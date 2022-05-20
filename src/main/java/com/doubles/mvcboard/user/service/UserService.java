package com.doubles.mvcboard.user.service;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

public interface UserService {

	//회원가입 처리
	void register(UserVO userVO) throws Exception;
	
	//로그인 처리
	UserVO login(LoginDTO loginDTO) throws Exception;
	
}
