package vn.iostar.dao;

import java.util.List;
import vn.iostar.entity.Video;

public interface IVideoDao {

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);
	
	Video findByTitle(String title) throws Exception;

	Video findById(String videoid);

	List<Video> findAll(int page, int pagesize);

	List<Video> searchByTitle(String title);

	List<Video> findAll();

	int count();

	}