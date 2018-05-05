package dao;

import java.util.Map;

import entity.User;

public interface UserDAO {
    void addUser(User user);
    User findUserByName(String name);
	User findUserById(String userId);
    void updateUser(Map<String,Object> map);
}
