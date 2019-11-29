package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.AlbumDao;
import com.baizhi.cmfz.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Album> queryByPage(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        return albumDao.queryByPage(start, rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryCount() {
        return albumDao.queryCount();
    }

    @Override
    public void add(Album album) {
        albumDao.add(album);
    }

    @Override
    public void update(Album album) {
        albumDao.update(album);
    }

    @Override
    public void deleteById(String id) {
        albumDao.deleteById(id);
    }

    @Override
    public void updateById(String id, String illustration) {
        albumDao.updateById(id, illustration);
    }

    @Override
    public void deleteByCount(String[] id) {
        albumDao.deleteCount(id);
    }
}
