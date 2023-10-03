package userSystem.Demo;
import java.util.Date;

import userSystem.dto.UserRegisterParam;

public class UserDemo {
	
	public static void main(String[] args) {
		
		UserRegisterParam userRegisterParam = new UserRegisterParam();
		userRegisterParam.setName("Alex");
		userRegisterParam.setAccount("a123");
		userRegisterParam.setEmail("pen@gmail.com");
		userRegisterParam.setSex("男");
		userRegisterParam.setBirthday(new Date());
		userRegisterParam.setPassword("12345");
		
		
	}

}
