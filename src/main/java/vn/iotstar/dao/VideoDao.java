package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.Video;

public interface VideoDao {
    Video findById(Integer id);
    List<Video> findAll();
    List<Video> search(String kw);  
    Video create(Video e);
    Video update(Video e);
    void delete(Integer id);
}
