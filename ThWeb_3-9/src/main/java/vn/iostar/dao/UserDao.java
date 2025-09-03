package vn.iostar.dao;

import vn.iostar.model.User;

public interface UserDao {
	User get(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void updatePassword(String email, String newPassword);
}