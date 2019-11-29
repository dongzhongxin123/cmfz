package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {

    List<Album> queryAll();

    List<Album> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer queryCount();

    void add(Album album);

    void update(Album album);

    void updateById(@Param("id") String id, @Param("illustration") String illustration);

    void deleteById(String id);

    void deleteCount(String[] id);
}
