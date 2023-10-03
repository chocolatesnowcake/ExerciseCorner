package userSystem.service;

import userSystem.model.ForgotPasswordParam;
import userSystem.model.LoginParam;
import userSystem.model.RegisterParam;
import userSystem.model.User;

public interface UserService {
	User register(RegisterParam registerParam);
	
	User checkAccount(String account);
	
	User login(LoginParam loginParam);
	
	void resetPassword(ForgotPasswordParam forgotPasswordParam);
	
	void deleteUser(Integer userId);
}
