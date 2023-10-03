package userSystem.action;

import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;

import userSystem.dto.LoginParam;
import userSystem.model.User;
import userSystem.service.UserService;

public class LoginAction extends ActionSupport {
	
	@Autowired
	UserService userService;
    /**
     * 
     */
	LoginParam loginParam;

    public LoginParam getLoginParam() {
		return loginParam;
	}

	public void setLoginParam(LoginParam loginParam) {
		this.loginParam = loginParam;
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

	public String execute(){
		if(loginParam == null) {
			return "login";
		}
		
    	user = userService.login(loginParam);
    	
        if(user != null){
            return "success";
        }else{
        	setMessage("wrong");
        	return "error";
        }    
    }
}

// 跳轉頁面參考: https://www.itread01.com/articles/1495138759.html