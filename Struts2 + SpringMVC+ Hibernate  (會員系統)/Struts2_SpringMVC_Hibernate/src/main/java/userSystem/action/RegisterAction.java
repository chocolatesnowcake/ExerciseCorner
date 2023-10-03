package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import userSystem.dto.RegisterParam;
import userSystem.model.User;
import userSystem.service.UserService;

public class RegisterAction extends ActionSupport{
	@Autowired
	UserService userService;
	
	RegisterParam registerParam;

	public RegisterParam getRegisterParam() {
		return registerParam;
	}

	public void setRegisterParam(RegisterParam registerParam) {
		this.registerParam = registerParam;
	}
	
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute() {
		if(registerParam == null) {
			return "register";
		}
		
		user = userService.register(registerParam);
		
		if(user != null) {
			return "success";
		}else {
			setMessage("wrong");
			return "error";
		}
	}
	
	
	
	
	
}
