package userSystem.dao;

import userSystem.dto.LoginParam;
import userSystem.dto.RegisterParam;
import userSystem.model.User;

public interface UserDao {
	
	User createUser(RegisterParam registerParam);
	
	User login(LoginParam loginParam);
	
	User checkAccount(String account);
	
	void resetPassword(Integer userId, String resetPassword);
	
	void deleteUser(Integer userId);
}
