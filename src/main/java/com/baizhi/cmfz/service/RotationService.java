package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Rotation;

import java.util.List;

public interface RotationService {

    List<Rotation> queryAll();

    List<Rotation> queryByPage(Integer page, Integer rows);

    Integer selectByCount();

    void add(Rotation rotation);

    void update(Rotation rotation);

    void deleteById(String id);

    void updateById(String id, String photo);

    void deleteByCount(String[] id);

    Rotation queryById(String id);


}
