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
			throw new NameException("���ܿ�");
		}
		if(password==null ||password.trim().isEmpty()){
			throw new PasswordException("����������");
		}
	
		//�����û��������û���Ϣ �ڼ�������Ƿ���ȷ
        User user = dao.findUserByName(name);
        if(user==null){
        	throw new NameException("�û�������");
        }
        //password ��������� ժҪ
        //String salt ="��ѧjava";
       // String md5 =DigestUtils.md5Hex(password+salt); 
        //��װ��������㷨
        String md5 =Util.saltMd5(password);
        //�Ƚ�ժҪ,���ժҪһ��������(����)һ��
        if(user.getPassword().equals(md5)){
        	String token = UUID.randomUUID().toString();
        	user.setToken(token);
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id", user.getId());
        	map.put("token", token);
        	dao.updateUser(map);
        	return user;
        }
		throw new PasswordException("�������");
	}
    @Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public User regist(String name, String descr, String password, String confirm)
			throws NameException, PasswordException {
		if(name==null || name.trim().isEmpty()){//˳���ܵߵ�,��ָ���쳣
			throw new NameException("�û�������Ϊ��");
		}
		if(password==null || password.trim().isEmpty()){
			throw new PasswordException("���벻��Ϊ��");
		}
		if(!password.equals(confirm)){//�����ָ���쳣
			throw new PasswordException("���벻һ��");
		}
		User user = dao.findUserByName(name);
		if(user!=null){
			throw new NameException("�û���ע��");
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
