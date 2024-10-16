package vn.iostar.dao;

import java.util.List;
import vn.iostar.entity.User;

public interface IUserDao {

	User findById(int userid);

	User findByUsername(String name) throws Exception;

	List<User> findAll(int page, int pagesize);

	User findByEmail(String email) throws Exception;

	List<User> searchByName(String username);

	List<User> findAll();

	int count();

	void delete(int userid) throws Exception;

	void update(User user);

	void insert(User user);

	User findByPhone(String phone) throws Exception;
}
