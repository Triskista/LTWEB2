package vn.iostar.service;

import java.util.List;

import vn.iostar.dao.IVideoDao;
import vn.iostar.dao.VideoDao;
import vn.iostar.entity.Video;

public interface IVideoService {

	Video findByTitle(String title);

	void update(Video video);

	void delete(String vidid) throws Exception;

	Video findById(String vidid);

	List<Video> findAll();

	List<Video> searchByTitle(String title);

	List<Video> findAll(int page, int pagesize);

	int count();

	void insert(Video video);

	IVideoDao viddao = new VideoDao();
}
