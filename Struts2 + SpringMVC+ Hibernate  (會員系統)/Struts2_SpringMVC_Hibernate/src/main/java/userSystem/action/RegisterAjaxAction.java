package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.opensymphony.xwork2.ActionSupport;

import userSystem.model.User;
import userSystem.service.UserService;

@RestController
public class RegisterAjaxAction extends ActionSupport{
	
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
	public String execute() throws Exception {
		
		if(account == null | account == "") {
			setMessage("帳號為必填欄位，請輸入帳號");
			return "notBlank";
		}else {
			User user = userService.checkAccount(account);
			
			if(user != null) {
				setMessage("此帳號已被註冊過，請重新輸入");
				return "registered";
			}else {
				setMessage("OK");
				return "OK";
			}
		}
		
	}
	
	
}
