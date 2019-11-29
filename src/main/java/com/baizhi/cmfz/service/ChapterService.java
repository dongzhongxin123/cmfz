package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Chapter;

import java.util.Map;

public interface ChapterService {

    //Integer queryCount();

    Map<String, Object> queryByPage(Integer page, Integer rows, String id);

    void deleteByCount(String[] id);

    void deleteById(String id);

    void update(Chapter chapter);

    void add(Chapter chapter);

    void updateById(Chapter chapter);
}
