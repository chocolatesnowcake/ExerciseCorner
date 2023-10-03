package userSystem.service;

import userSystem.dto.LoginParam;
import userSystem.dto.RegisterParam;
import userSystem.model.ForgotPasswordParam;
import userSystem.model.User;

public interface UserService {
	
	User register(RegisterParam registerParam);
	
	User login(LoginParam loginParam);
	
	User checkAccount(String account);
	
	void deleteUser(Integer userId);
	
	void resetPassword(String account, String resetPassword);
	
	void forgotPassword(String account);
}
