package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import userSystem.model.User;
import userSystem.service.UserService;

public class ForgotPasswordAction extends ActionSupport{
	
	@Autowired
	UserService userService;
	
	String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
		if(account == null) {
			return "forgotPassword";
		}
		// 先確認該帳號是否經過註冊
		User user = userService.checkAccount(account);
		if(user == null) {
			setMessage("wrong");
			return "error";
		}
		
		userService.forgotPassword(account);
		setMessage("success");
		return "success";
	}
	
}
