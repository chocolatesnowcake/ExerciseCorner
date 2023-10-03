package userSystem.service;

import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import userSystem.dao.UserDao;
import userSystem.dto.LoginParam;
import userSystem.dto.RegisterParam;
import userSystem.model.ForgotPasswordParam;
import userSystem.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User register(RegisterParam registerParam) {
		// 確認帳號是否經過註冊
		User user = userDao.checkAccount(registerParam.getAccount());
		if(user != null) {
			return null;
		}
		
		// 生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(registerParam.getPassword().getBytes());
		
		registerParam.setPassword(hashedPassword);
	
		return userDao.createUser(registerParam);
	}

	@Override
	public User login(LoginParam loginParam) {
		// 生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
		loginParam.setPassword(hashedPassword);
		
		return userDao.login(loginParam);
	}
	
	@Override
	public User checkAccount(String account) {
		
		return userDao.checkAccount(account);
	}

	@Override
	public void deleteUser(Integer userId) {
		
		userDao.deleteUser(userId);
	}

	public void resetPassword(String account, String resetPassword) {
		// 先找到對應的帳號
		User user = userDao.checkAccount(account);
		
		// 生成 user 設定的新密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(resetPassword.getBytes());
		
		// 完成重設密碼的步驟
		userDao.resetPassword(user.getUserId(), hashedPassword);
	}
	
	@Override
	public void forgotPassword(String account) {
		Random random = new Random();
		// 設置亂數密碼
		String randomPassword = String.format("%08d", random.nextInt(99999999));
		
		User user = userDao.checkAccount(account);
		// 寄信至信箱
		// 寄出新郵件
		try {
            //从tomcat中获取JNDI容器
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            //JNDI中获取Mail的Session对象
            Session session = (Session) envCtx.lookup("mail/Session");
            
            //新建一个邮件
            Message message = new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress("penny90149@gmail.com"));
            //设置多个收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            //设置主题
            message.setSubject("忘記密碼信件");
            //设置文本
            message.setText("親愛的 " + user.getName() + " 您好，您的新密碼為：" + randomPassword + "。為了您的帳戶安全，請您於登入後盡速重設密碼，謝謝。");
            
            //邮件传送对象
            Transport transport = session.getTransport();
            //设置，邮件服务器地址、用户名、密码
            transport.connect("smtp.gmail.com", "penny90149@gmail.com", "ipcwgcyqirfxhjta");
            //发送邮件（邮件，地址）
            transport.sendMessage(message,  new Address[] {new InternetAddress(user.getEmail())} );
            //关闭连接
            transport.close();
            
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
		
		// 生成密碼雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(randomPassword.getBytes());
				
		// 呼叫修改密碼的方法
		userDao.resetPassword(user.getUserId(), hashedPassword);
	}
	
}
