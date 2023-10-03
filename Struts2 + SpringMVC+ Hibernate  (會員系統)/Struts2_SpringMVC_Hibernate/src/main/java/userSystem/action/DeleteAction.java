package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import userSystem.model.User;
import userSystem.service.UserService;

public class DeleteAction extends ActionSupport{

	@Autowired
	UserService userService;
	
	Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
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
	public String execute() throws Exception {
		if(account == null) {
			return "delete";
		}
		// 先確認該帳號經過註冊
		User user = userService.checkAccount(account);
		
		if(user != null) {
			setMessage("success");
			userService.deleteUser(userId);
			
			return "success";
		}else {
			setMessage("error");
			return "error";
		}
		
		
		
		
	}
	
}
