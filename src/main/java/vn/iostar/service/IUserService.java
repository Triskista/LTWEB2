package vn.iostar.service;

import java.util.List;

import vn.iostar.dao.IUserDao;
import vn.iostar.dao.UserDao;
import vn.iostar.entity.User;

public interface IUserService {
	
	User findByUsername(String name);

	void update(User user);

	void delete(int userid) throws Exception;

	User findById(int userid);

	List<User> findAll();
	void insert(User user);

	List<User> searchByName(String username);

	List<User> findAll(int page, int pagesize);

	int count();

	User findByEmail(String email);

	User findByPhone(String phone);

	User register(User user);

	User login(String username, String password);

	IUserDao userDao = new UserDao();
}
