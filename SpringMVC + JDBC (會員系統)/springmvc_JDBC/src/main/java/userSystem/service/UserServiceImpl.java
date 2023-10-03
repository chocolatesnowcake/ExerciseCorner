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
import userSystem.dto.UserForgotPasswordParam;
import userSystem.dto.UserLoginParam;
import userSystem.dto.UserRegisterParam;
import userSystem.model.User;

@Service
public class UserServiceImpl implements UserService{
	Random random = new Random();
	@Autowired
	private UserDao userDao;
	
	public User checkUserAccount(String account) {
		
		return userDao.checkAccount(account);
	}
	
	@Override
	public User register(UserRegisterParam userRegisterParam) {
		// 使用 MD5 加密密碼
		String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterParam.getPassword().getBytes());
		userRegisterParam.setPassword(hashedPassword);
		
		// 確認帳號是否已被註冊
		User user = userDao.checkAccount(userRegisterParam.getAccount());
		if(user != null) {
			return null;
		}
		
		Integer userId = userDao.createUser(userRegisterParam);
		User createdUser = userDao.getUserById(userId);
		
		return createdUser;
	}

	@Override
	public User login(UserLoginParam userLoginParam) {
		
		// 先將登入的密碼也轉換成雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(userLoginParam.getPassword().getBytes());
		userLoginParam.setPassword(hashedPassword);
		
		return userDao.login(userLoginParam);
	}

	@Override
	public void sendResetPasswordMail(UserForgotPasswordParam userForgotPasswordParam) {
		// 先獲得 User 資料
		User user = userDao.getUserByAccount(userForgotPasswordParam);
		// 再重新設定亂數密碼
		String randomPassword = String.format("%06d", random.nextInt(999999));
		
		// 將亂數密碼也轉成雜湊值儲存
		String hashedPassword = DigestUtils.md5DigestAsHex(randomPassword.getBytes());
		
		userDao.resetPasswordByAccount(user.getAccount(), hashedPassword);
		
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
            message.setText("親愛的 " + user.getName() + " 您好，您的新密碼為：" + randomPassword + "。");
            
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
	}

}

//https://blog.csdn.net/sinat_35512245/article/details/71087162
// javaMail 參考: https://www.cnblogs.com/h--d/p/6139509.html
// https://stackoverflow.com/questions/66293345/unsupported-or-unrecognized-ssl-message context.xml加一些設定
// https://www.gushiciku.cn/pl/gInq/zh-tw 使用了方法二 刪除SSLv3, TLSv1, TLSv1.1並儲存java.security檔案