package userSystem.dao;

import userSystem.dto.UserForgotPasswordParam;
import userSystem.dto.UserLoginParam;
import userSystem.dto.UserRegisterParam;
import userSystem.model.User;

public interface UserDao {
	public User checkAccount(String account);
	
	public Integer createUser(UserRegisterParam userRegisterParam);

	public User getUserById(Integer userId);
	
	public User login(UserLoginParam userLoginParam);
	
	public User getUserByAccount(UserForgotPasswordParam userForgotPasswordParam);
	
	public void resetPasswordByAccount(String account, String resetPassword);
	
}
