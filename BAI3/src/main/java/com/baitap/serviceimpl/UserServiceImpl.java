	package com.baitap.serviceimpl;
	
	import com.baitap.dao.UserDao;
	import com.baitap.daoimpl.UserDaoImpl;
	import com.baitap.model.User;
	import com.baitap.service.UserService;
	
	public class UserServiceImpl implements UserService {
		UserDao userDao = new UserDaoImpl();
		
		@Override
		public User login(String username, String password) {
			User user = this.get(username);
			if (user != null && password.equals(user.getPassWord())) {
				return user;
			}
			return null;
		}
		
		@Override
		public User get(String username) {
			return userDao.get(username);
		}
		
		@Override
	    public void insert(User user) {
	        userDao.insert(user);
	    }
	
		@Override
		public boolean register(String username, String password, String email, String fullname, String phone) {
	
		    if (userDao.checkExistUsername(username)) {
		        return false;
		    }
	
		    if (userDao.checkExistEmail(email)) {
		        return false;
		    }
	
		    if (userDao.checkExistPhone(phone)) {
		        return false;
		    }
	
		    long millis = System.currentTimeMillis();
		    java.sql.Date date = new java.sql.Date(millis);
	
		    User user = new User(
		        0,
		        email,
		        username,
		        fullname,
		        password,
		        null,
		        5,
		        phone,
		        date
		    );
	
		    userDao.insert(user);
	
		    return true;
		}
		
	    @Override
	    public boolean checkExistEmail(String email) {
	        return userDao.checkExistEmail(email);
	    }
	
	    @Override
	    public boolean checkExistUsername(String username) {
	        return userDao.checkExistUsername(username);
	    }
	
	    @Override
	    public boolean checkExistPhone(String phone) {
	        return userDao.checkExistPhone(phone);
	    }
	    @Override
	    public boolean updateProfile(User user) {
	        return userDao.updateProfile(user);
	    }
	}