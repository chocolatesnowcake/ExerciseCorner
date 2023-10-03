package userSystem.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import userSystem.dto.UserForgotPasswordParam;
import userSystem.dto.UserLoginParam;
import userSystem.dto.UserRegisterParam;
import userSystem.model.User;
import userSystem.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/registerProccess", method=RequestMethod.GET)
	public ModelAndView enterRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("userRegister");
		mav.addObject("userRegisterParam", new UserRegisterParam());
		
		return mav;
	}	
	
	@RequestMapping(value="register/user", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userRegisterParam") UserRegisterParam userRegisterParam) {
		ModelAndView mav = null;
		
		User user = userService.register(userRegisterParam);
		
		if(user != null) {
			mav = new ModelAndView("userRegisterSuccess");
			mav.addObject("user", user);
		}else {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "wrong");
			
			mav = new ModelAndView("redirect:/registerProccess", map);
		}
		return mav;
	}
	
	@RequestMapping(value="/loginProccess", method=RequestMethod.GET)
	public ModelAndView enterLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("userLogin");
		mav.addObject("userLoginParam", new UserLoginParam());
		
		return mav;
	}
	
	@RequestMapping(value="login/user", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userLoginParam") UserLoginParam userLoginParam) {
		ModelAndView mav = null;
		
		User user = userService.login(userLoginParam);
		
		if(user != null) {
			mav = new ModelAndView("userLoginSuccess");
			mav.addObject("user", user);
		}else {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "wrongPassword");
			
			mav = new ModelAndView("redirect:/loginProccess", map);
		}
		return mav;
    }
	
	@RequestMapping(value="/passwordProccess", method=RequestMethod.GET)
	public ModelAndView enterPasswordForgot(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("userForgotPassword");
		mav.addObject("userForgotPasswordParam", new UserForgotPasswordParam());
		
		return mav;
	}	
	
	@RequestMapping(value="findPassword/user", method=RequestMethod.POST)
	public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userForgotPasswordParam") UserForgotPasswordParam userForgotPasswordParam) {
		
		ModelAndView mav = new ModelAndView("userForgotPassword");
		mav.addObject("message", "將發送新密碼至信箱，請至註冊信箱確認收件");
		
		userService.sendResetPasswordMail(userForgotPasswordParam);

		return mav;
	}
	
	// ajax 判斷 參考: https://blog.csdn.net/sinat_33087001/article/details/120228438
	
	@RequestMapping("/registerAccount")
    public String registerAccount(String account){
		String message = "";
		
		if(account == null | account == "") {
			message = "帳號為必填欄位，請輸入帳號";
			
		}else {
			User user = userService.checkUserAccount(account);
			
			if(user != null) {
				message = "此帳號已被註冊，請輸入新帳號";
			}else {
				message = "OK";
			}
		}
		return message;
    }
	
	@RequestMapping("/registerPassword")
    public String registerPassword(String password){
		String message = "";

		if(password == null | password == "") {
			message = "密碼為必填欄位，請輸入密碼";
		}else {
			message = "OK";
		}
        return message; 
    }
	
	@RequestMapping("/registerName")
    public String registerName(String name){
		String message = "";

		if(name == null | name == "") {
			message = "姓名為必填欄位，請輸入姓名";
		}else {
			message = "OK";
		}
        return message; 
    }
	
	@RequestMapping("/registerEmail")
    public String registerEmail(String email){
		String message = "";

		if(email == null | email == "") {
			message = "電子信箱為必填欄位，請輸入電子信箱";
		}else {
			message = "OK";
		}
        return message; 
    }
	
	@RequestMapping("/loginAccount")
    public String loginAccount(String account){
		User user = userService.checkUserAccount(account);
		
		String message = "";
		
		if(user != null) {
			message = "此為已註冊帳號";
		}else {
			message = "此為未註冊過帳號，請重新輸入";
		}
		return message;
    }
	
//	ajax 使用教學 https://blog.csdn.net/sinat_33087001/article/details/120228438
	
}	
//	https://hackmd.io/@bynum5566/Hy9iQXKZP
//	範例
//	@RequestMapping("/user/register")
//	public ModelAndView enterRegister(@RequestParam(value = "name")String name) {
//		ModelAndView mv = new ModelAndView("userRegister");
//		mv.addObject("name",name);
//		
//		return mv;
//	}
//


/*spring-webmvc 的實作上，可以用 @Controller 標示擔任控制器的類別，至於 URL 對應，
 * 則使用 @RequestMapping，這邊的設定表示 "/hello" 的請求會由 hello 方法處理。
 * 
 * 如果有請求參數，方法上可使用 @RequestParam 標註哪個請求參數指定給哪個方法參數，
 * Model 參數用來攜帶回應頁面要用到的資料，屬性名稱設定為 "user" 時，在 JSP 中可使用 ${user} 來取得，
 * 方法最後傳回 "hello"，根據先前 InternalResourceViewResolver 的設定，回應的頁面將會是 "/hello.jsp"。
 * 
 * 
 * 註冊、登入功能實作參考: https://blog.csdn.net/boonya/article/details/60601935
 *  https://dzone.com/articles/spring-mvc-example-for-user-registration-and-login-1?edition=274902&utm_source=Daily%20Digest&utm_medium=email&utm_campaign=dd%202017-03-04
 * 
 */
