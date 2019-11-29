package com.baizhi.cmfz.dao;


import com.baizhi.cmfz.entity.Rotation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RotationDao {

    List<Rotation> queryAll();

    List<Rotation> findAllDeptByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer findByCount();

    void add(Rotation rotation);

    void update(Rotation rotation);

    void updateById(@Param("id") String id, @Param("photo") String photo);

    void deleteById(String id);

    void deleteCount(String[] id);

    Rotation queryById(String id);
}
