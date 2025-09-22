package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Video;

public interface VideoService {
    Video findById(Integer id);
    List<Video> findAll();
    List<Video> search(String kw);
    Video save(Video e);   
    void delete(Integer id);
}
