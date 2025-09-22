package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.VideoDao;
import vn.iotstar.dao.impl.VideoDaoImpl;
import vn.iotstar.entity.Video;
import vn.iotstar.services.VideoService;

public class VideoServiceImpl implements VideoService {

    private final VideoDao dao = new VideoDaoImpl();

    @Override
    public Video findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Video> search(String kw) {
        return dao.search(kw);
    }

    @Override
    public Video save(Video e) {
        if (e.getId() == null) {
            return dao.create(e);
        }
        return dao.update(e);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
