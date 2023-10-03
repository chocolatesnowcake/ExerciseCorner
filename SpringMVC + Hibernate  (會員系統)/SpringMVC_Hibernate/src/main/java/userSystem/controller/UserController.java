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

import userSystem.model.ForgotPasswordParam;
import userSystem.model.LoginParam;
import userSystem.model.RegisterParam;
import userSystem.model.User;
import userSystem.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/registerProccess", method=RequestMethod.GET)
	public ModelAndView enterRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("registerParam", new RegisterParam());
		
		return mav;
	}	
	
	@RequestMapping(value="register/user", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("registerParam") RegisterParam registerParam) {
		ModelAndView mav = null;
		User user = userService.register(registerParam);
		
		if(user != null) {
			mav = new ModelAndView("registerSuccess");
		}else {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "wrong");
			
			mav = new ModelAndView("redirect:/registerProccess", map);
		}
		return mav;
	}
	
	@RequestMapping(value="/loginProccess", method=RequestMethod.GET)
	public ModelAndView enterLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginParam", new LoginParam());
		
		return mav;
	}
	
	@RequestMapping(value="login/user", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("loginParam") LoginParam loginParam) {
		ModelAndView mav = null;
		User user = userService.login(loginParam);
		
		if(user != null) {
			mav = new ModelAndView("loginSuccess");
			mav.addObject("user", user);
		}else {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "wrong");
			
			mav = new ModelAndView("redirect:/loginProccess", map);
		}
		
		return mav;
    }
	
	@RequestMapping(value="/passwordProccess", method=RequestMethod.GET)
	public ModelAndView enterforgotPassword(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("forgotPassword");
		mav.addObject("forgotPasswordParam", new ForgotPasswordParam());
		
		return mav;
	}
	
	@RequestMapping(value="password/user", method=RequestMethod.POST)
	public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("forgotPasswordParam") ForgotPasswordParam forgotPasswordParam) {
		userService.resetPassword(forgotPasswordParam);
		
		Map<String, Object> map = new HashMap<>();
		map.put("message", "success");
			
		ModelAndView mav = new ModelAndView("redirect:/passwordProccess", map);
		
		return mav;
    }
	
	@RequestMapping(value="/delete/user", method=RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("user") User user) {
		userService.deleteUser(user.getUserId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("message", "success");
		
		ModelAndView mav = new ModelAndView("redirect:/loginProccess", map);
		
		return mav;
    }
	
	// ajax 方法
	@RequestMapping("/registerAccount")
    public String registerAccount(String account){
		String message = "";
		
		if(account == null | account == "") {
			message = "帳號為必填欄位，請輸入帳號";
		}else {
			User user = userService.checkAccount(account);
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
			message = "密碼為必填欄位，請輸入帳號";
		}else {
			message = "OK";
			}
		return message;
    }
}
