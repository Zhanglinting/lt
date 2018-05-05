package service;



import entity.User;

/**
 * ҵ���ӿ�
 * �������ҵ���ܷ���
 * @author Administrator
 * 
 */

public interface UserService {
	/**
	 * ��¼���ܷ���
	 * @param name �û���
	 * @param password ����
	 * @return ��¼�ɹ�ʱ���ص�¼���û���
	 * 
	 */
    User login(String name,String password);
    /**
     * ע���û�����
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
