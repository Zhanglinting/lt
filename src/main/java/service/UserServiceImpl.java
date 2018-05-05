package service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDAO;
import entity.User;
import util.Util;
@Service("userService")
public class UserServiceImpl implements UserService {
     @Resource
     private UserDAO dao;
     @Transactional
	public User login(String name, String password) throws NameException, PasswordException {
		 System.out.println("login");
		if(name==null || name.trim().isEmpty()){
			throw new NameException("不能空");
		}
		if(password==null ||password.trim().isEmpty()){
			throw new PasswordException("请输入密码");
		}
	
		//根据用户名查找用户信息 在检查密码是否正确
        User user = dao.findUserByName(name);
        if(user==null){
        	throw new NameException("用户名错误");
        }
        //password 密码的明文 摘要
        //String salt ="我学java";
       // String md5 =DigestUtils.md5Hex(password+salt); 
        //封装密码加密算法
        String md5 =Util.saltMd5(password);
        //比较摘要,如果摘要一样则明文(密码)一样
        if(user.getPassword().equals(md5)){
        	String token = UUID.randomUUID().toString();
        	user.setToken(token);
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id", user.getId());
        	map.put("token", token);
        	dao.updateUser(map);
        	return user;
        }
		throw new PasswordException("密码错误");
	}
    @Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public User regist(String name, String descr, String password, String confirm)
			throws NameException, PasswordException {
		if(name==null || name.trim().isEmpty()){//顺序不能颠倒,空指针异常
			throw new NameException("用户名不能为空");
		}
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(!password.equals(confirm)){//避免空指针异常
			throw new PasswordException("密码不一致");
		}
		User user = dao.findUserByName(name);
		if(user!=null){
			throw new NameException("用户已注册");
		}
		if(descr==null || descr.trim().isEmpty()){
			descr = name;
		}
		  String id = UUID.randomUUID().toString();
		   String token ="";
		   password = Util.saltMd5(password);
		  user = new User(id,name,password,token,descr);
		  dao.addUser(user);
		return user;
	}
    @Transactional
	public boolean checkToken(String userId, String token) {
		User user = dao.findUserById(userId);	
		return token.equals(user.getToken());
	}

}
