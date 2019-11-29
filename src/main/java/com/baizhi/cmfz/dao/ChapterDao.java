package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {

    Integer queryNum();

    List<Chapter> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("id") String id);

    void update(Chapter chapter);

    void deleteByCount(String[] id);

    void deleteById(String id);

    void add(Chapter chapter);

    void updateById(Chapter chapter);
}
