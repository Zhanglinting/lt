package service;



import entity.User;

/**
 * 业务层接口
 * 声明软件业务功能方法
 * @author Administrator
 * 
 */

public interface UserService {
	/**
	 * 登录功能方法
	 * @param name 用户名
	 * @param password 密码
	 * @return 登录成功时返回登录的用户名
	 * 
	 */
    User login(String name,String password);
    /**
     * 注册用户功能
     * @param name
     * @param descr
     * @param password
     * @param confirm
     * @return
     */
    User regist(String name,String descr,String password,String confirm)
    		throws NameException,PasswordException;
	boolean checkToken(String userId, String token);
}
