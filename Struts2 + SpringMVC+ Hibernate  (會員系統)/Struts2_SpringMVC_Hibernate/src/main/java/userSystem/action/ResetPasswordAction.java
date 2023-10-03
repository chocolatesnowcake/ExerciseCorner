package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import userSystem.model.User;
import userSystem.service.UserService;

public class ResetPasswordAction extends ActionSupport{
	@Autowired
	UserService userService;
	
	String account;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute(){
		if(password == null) {
			return "resetPassword";
		}

		userService.resetPassword(account, password);
		setMessage("success");
		return "success";
		
	}
	
}
