package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> queryByPage(Integer page, Integer rows);

    Integer queryCount();

    void add(Album album);

    void update(Album album);

    void deleteById(String id);

    void updateById(String id, String illustration);

    void deleteByCount(String[] id);
}
