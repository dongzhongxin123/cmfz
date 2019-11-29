package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.ChapterDao;
import com.baizhi.cmfz.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

   /* @Override
    public Integer queryCount() {
        return chapterDao.queryCount();
    }*/

    @Override
    public Map<String, Object> queryByPage(Integer page, Integer rows, String id) {
        HashMap<String, Object> map = new HashMap<>();
        Integer count = chapterDao.queryNum();
        Integer start = (page - 1) * rows;
        List<Chapter> chapters = chapterDao.queryByPage(start, rows, id);
        long total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total", total);
        map.put("page", page);
        map.put("records", count);
        map.put("rows", chapters);
        return map;
    }

    @Override
    public void deleteByCount(String[] id) {
        chapterDao.deleteByCount(id);
    }

    @Override
    public void deleteById(String id) {
        chapterDao.deleteById(id);
    }

    @Override
    public void update(Chapter chapter) {
        chapterDao.update(chapter);
    }

    @Override
    public void add(Chapter chapter) {
        chapterDao.add(chapter);
    }

    @Override
    public void updateById(Chapter chapter) {
        chapterDao.updateById(chapter);
    }


}
