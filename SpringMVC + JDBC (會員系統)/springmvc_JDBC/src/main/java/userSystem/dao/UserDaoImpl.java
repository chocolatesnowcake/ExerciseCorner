package userSystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import userSystem.dto.UserForgotPasswordParam;
import userSystem.dto.UserLoginParam;
import userSystem.dto.UserRegisterParam;
import userSystem.model.User;
import userSystem.rowmapper.UserRowMapper;

/**
 * 
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public User checkAccount(String account) {
		String sql = "SELECT user_id, account, name, email, birthday, sex, password FROM user WHERE account = :account";
		Map<String, Object> map = new HashMap<>();
		map.put("account", account);
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else {
			return null;
		}
	}
	
	public Integer createUser(UserRegisterParam userRegisterParam) {
		String sql = "INSERT INTO user(account, name, email, birthday, sex, password) "
				+ "VALUES(:account, :name, :email, :birthday, :sex, :password)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("account", userRegisterParam.getAccount());
		map.put("name", userRegisterParam.getName());
		map.put("email", userRegisterParam.getEmail());
		map.put("birthday", userRegisterParam.getBirthday());
		map.put("sex", userRegisterParam.getSex());
		map.put("password", userRegisterParam.getPassword());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
		
		int userId = keyHolder.getKey().intValue();
		
		return userId;
	}
	
	public User getUserById(Integer userId) {
		String sql = "SELECT user_id, account, name, email, birthday, sex, password FROM user WHERE user_id = :userId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public User login(UserLoginParam userLoginParam) {
		String sql = "SELECT user_id, account, name, email, birthday, sex, password FROM user WHERE account = :account AND password = :password";
		
		Map<String, Object> map = new HashMap<>();
		map.put("account", userLoginParam.getAccount());
		map.put("password", userLoginParam.getPassword());
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public User getUserByAccount(UserForgotPasswordParam userForgotPasswordParam) {
		String sql = "SELECT user_id, account, name, email, birthday, sex, password FROM user WHERE account = :account";
		
		Map<String, Object> map = new HashMap<>();
		map.put("account", userForgotPasswordParam.getAccount());
		
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public void resetPasswordByAccount(String account , String resetPassword) {
		String sql = "UPDATE user SET password = :password WHERE account = :account";
		
		Map<String, Object> map = new HashMap<>();
		map.put("account", account);
		map.put("password", resetPassword);
		
		namedParameterJdbcTemplate.update(sql, map);
	}

	
}
