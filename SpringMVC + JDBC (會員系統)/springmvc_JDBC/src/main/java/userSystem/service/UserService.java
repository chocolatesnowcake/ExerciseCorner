package userSystem.service;

import userSystem.dto.UserForgotPasswordParam;
import userSystem.dto.UserLoginParam;
import userSystem.dto.UserRegisterParam;
import userSystem.model.User;

public interface UserService {
	User checkUserAccount(String account);
	
	User register(UserRegisterParam userRegisterParam);
	
	User login(UserLoginParam userLoginParam);
	
	void sendResetPasswordMail(UserForgotPasswordParam userForgotPasswordParam);
}
