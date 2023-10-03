package userSystem.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import userSystem.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setAccount(rs.getString("account"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setBirthday(rs.getTimestamp("birthday"));
		user.setSex(rs.getString("sex"));
		user.setPassword(rs.getString("password"));
		
		return user;
	}
	
	
}
