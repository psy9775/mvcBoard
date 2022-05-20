package com.doubles.mvcboard.user.controller;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;
import com.doubles.mvcboard.user.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserRegisterController {

	private final UserService userService;

	@Inject
	public UserRegisterController(UserService userService) {
		this.userService = userService;
	}

	//회원가입 페이지 이동
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "/user/register";
	}

	//회원가입 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
		String hashedPw = BCrypt.hashpw(userVO.getUserPw(), BCrypt.gensalt());
		userVO.setUserPw(hashedPw);
		userService.register(userVO);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		return "redirect:/user/login";
	}
	
	//로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO) throws Exception {
		return "/user/login";
	}
	
	//로그인 처리
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO loginDTO, HttpSession httpSession, Model model) throws Exception {
		UserVO userVO = userService.login(loginDTO);
		if(userVO == null || !BCrypt.checkpw(loginDTO.getUserPw(), userVO.getUserPw())){
			return;
		}
		
		model.addAttribute("user", userVO);
	}
	
}
