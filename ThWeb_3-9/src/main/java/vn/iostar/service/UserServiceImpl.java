package vn.iostar.service;
import vn.iostar.dao.UserDao;
import vn.iostar.dao.UserDaoImpl;
import vn.iostar.model.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.get(username);
        if (user != null && user.getPassWord().trim().equals(password.trim())) {
            return user;
        }
        return null;
    }
    public User get(String username) {
    	return userDao.get(username);
    }
    @Override
    public boolean register(String username, String password, String email, String 
    fullname, String phone ) {
	    if (userDao.checkExistUsername(username)) {
	    return false;
	    }
	    long millis=System.currentTimeMillis(); 
	    java.sql.Date date=new java.sql.Date(millis);
	    userDao.insert(new User(email, username, fullname,password,null,5,phone,date));
	    return true;
    }
    public boolean checkExistEmail(String email) {
    	return userDao.checkExistEmail(email);
    }
    public boolean checkExistUsername(String username) {
    	return userDao.checkExistUsername(username);
    }
    @Override
    public boolean checkExistPhone(String phone) {
    	return userDao.checkExistPhone(phone);
    }
    @Override
    public void insert(User user) {
    	userDao.insert(user);
    }
    @Override
    public void updatePassword(String email, String newPassword) {
        userDao.updatePassword(email, newPassword);
    }
}


