package com.doubles.mvcboard.user.service;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;
import com.doubles.mvcboard.user.persistence.UserDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Inject
	public UserServiceImpl(UserDAO userDAO){
		this.userDAO = userDAO;
	}

	//회원가입 처리
	@Override
	public void register(UserVO userVO) throws Exception {
		userDAO.register(userVO);
	}
	
	//로그인 처리
	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		return userDAO.login(loginDTO);
	}
}
