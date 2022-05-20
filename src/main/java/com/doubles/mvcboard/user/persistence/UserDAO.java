package com.doubles.mvcboard.user.persistence;

import com.doubles.mvcboard.user.domain.LoginDTO;
import com.doubles.mvcboard.user.domain.UserVO;

public interface UserDAO {

	//회원가입 처리
	void register(UserVO userVO) throws Exception;

	//로그인 처리
	UserVO login(LoginDTO loginDTO) throws Exception;

}
