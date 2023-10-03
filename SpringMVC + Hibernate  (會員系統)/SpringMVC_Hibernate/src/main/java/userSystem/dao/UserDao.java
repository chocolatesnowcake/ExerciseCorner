package userSystem.dao;

import userSystem.model.LoginParam;
import userSystem.model.RegisterParam;
import userSystem.model.User;

public interface UserDao {
	User createUser(RegisterParam registerParam);
	
	User checkAccount(String account);
	
	User login(LoginParam loginParam);
	
	void resetPassword(Integer userId, String resetPassword);
	
	void deleteUser(Integer userId);
}
