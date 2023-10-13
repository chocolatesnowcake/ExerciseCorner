package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

import userSystem.dto.AjaxMessage;
import userSystem.model.User;
import userSystem.service.UserService;

public class CheckAccountAction extends ActionSupport{
	
	@Autowired
	UserService userService;
	
	String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	private AjaxMessage ajaxMessage;
	
	public AjaxMessage getAjaxMessage() {
		return ajaxMessage;
	}

	public void setAjaxMessage(AjaxMessage ajaxMessage) {
		this.ajaxMessage = ajaxMessage;
	}

	@Override
	public String execute() throws Exception {
		ajaxMessage = new AjaxMessage();
		
		if(account == null || account.equals("")) {
			ajaxMessage.setMessage("帳號為必填欄位，請輸入帳號");
		}else {
			User user = userService.checkAccount(account);
			if(user != null) {
			ajaxMessage.setMessage("該帳號已被註冊，請輸入新帳號");
			}else {
				ajaxMessage.setMessage("OK");
			}
		}

		return SUCCESS;
	}
	
}
