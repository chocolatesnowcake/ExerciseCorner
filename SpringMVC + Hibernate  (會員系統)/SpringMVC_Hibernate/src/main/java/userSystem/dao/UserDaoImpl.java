package userSystem.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import userSystem.model.LoginParam;
import userSystem.model.RegisterParam;
import userSystem.model.User;

// Hibernate 增刪改查操作: http://c.biancheng.net/hibernate/crud.html
@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public User createUser(RegisterParam registerParam) {
		//加载 Hibernate 核心配置文件
	    Configuration configuration = new Configuration().configure();
	    //创建一个 SessionFactory 用来获取 Session 连接对象
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    //获取session 连接对象
	    Session session = sessionFactory.openSession();
	    //开始事务
	    Transaction transaction = session.beginTransaction();
	    //创建实体类对象
	    User user = new User();
	    user.setAccount(registerParam.getAccount());
	    user.setPassword(registerParam.getPassword());
	    user.setName(registerParam.getName());
	    user.setEmail(registerParam.getEmail());
	    user.setBirthday(registerParam.getBirthday());
	    user.setSex(registerParam.getSex());
	    user.setCreatedDate(new Date());
	    
	    //向 user 表中插入数据,返回值为新增数据的主键 id
	    Serializable save = session.save(user);
	    
	    //提交事务
	    transaction.commit();
	    //释放资源
	    session.close();
	    sessionFactory.close();
	    
		return user;
	}

	@Override
	public User checkAccount(String account) {
		//加载 Hibernate 核心配置文件
	    Configuration configuration = new Configuration().configure();
	    //创建一个 SessionFactory 用来获取 Session 连接对象
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    //获取session 连接对象
	    Session session = sessionFactory.openSession();
	    //开始事务
	    Transaction transaction = session.beginTransaction();
	    //创建 HQL 语句，语法与 SQL 类似，但操作的是实体类及其属性
	    Query<User> query = session.createQuery("from User where account = ?1");
	    //查询用户
	    query.setParameter(1, account);
	    //获取结果集
	    List<User> resultList = query.getResultList();
	    //提交事务
	    transaction.commit();
	    //释放资源
	    session.close();
	    sessionFactory.close();
		
	    if(resultList.size() > 0) {
	    	return resultList.get(0);
	    }else {
	    	return null;
	    }
	}

	@Override
	public User login(LoginParam loginParam) {

		//加载 Hibernate 核心配置文件
	    Configuration configuration = new Configuration().configure();
	    //创建一个 SessionFactory 用来获取 Session 连接对象
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    //获取session 连接对象
	    Session session = sessionFactory.openSession();
	    //开始事务
	    Transaction transaction = session.beginTransaction();
	    //创建 HQL 语句，语法与 SQL 类似，但操作的是实体类及其属性
	    Query<User> query = session.createQuery("from User where account = ?1 AND password = ?2");
	    //查询用户
	    query.setParameter(1, loginParam.getAccount());
	    query.setParameter(2, loginParam.getPassword());
	    //获取结果集
	    List<User> resultList = query.getResultList();
	    //提交事务
	    transaction.commit();
	    //释放资源
	    session.close();
	    sessionFactory.close();
		
	    if(resultList.size() > 0) {
	    	return resultList.get(0);
	    }else {
	    	return null;
	    }
	}

	@Override
	public void resetPassword(Integer userId, String resetPassword) {
		//加载 Hibernate 核心配置文件
	    Configuration configuration = new Configuration().configure();
	    //创建一个 SessionFactory 用来获取 Session 连接对象
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    //获取session 连接对象
	    Session session = sessionFactory.openSession();
	    //开始事务
	    Transaction transaction = session.beginTransaction();
	    //现将需要修改的记录查询出来
	    User user = session.get(User.class, userId);
	    //设置需要修改的字段
	    user.setPassword(resetPassword);
	    //直接调用 update() 方法进行修改
	    session.update(user);
	    //提交事务
	    transaction.commit();
	    //释放资源
	    session.close();
	    sessionFactory.close();
	}
	
	public void deleteUser(Integer userId) {
		//加载 Hibernate 核心配置文件
	    Configuration configuration = new Configuration().configure();
	    //创建一个 SessionFactory 用来获取 Session 连接对象
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    //获取session 连接对象
	    Session session = sessionFactory.openSession();
	    //开始事务
	    Transaction transaction = session.beginTransaction();
	    User user = new User();
	    user.setUserId(userId);
	    //删除指定的记录
	    session.delete(user);
	    //提交事务
	    transaction.commit();
	    //释放资源
	    session.close();
	    sessionFactory.close();
	}

}

//https://blog.csdn.net/sinat_35512245/article/details/71087162
//javaMail 參考: https://www.cnblogs.com/h--d/p/6139509.html
//https://stackoverflow.com/questions/66293345/unsupported-or-unrecognized-ssl-message context.xml加一些設定
//https://www.gushiciku.cn/pl/gInq/zh-tw 使用了方法二 刪除SSLv3, TLSv1, TLSv1.1並儲存java.security檔案
