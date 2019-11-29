package com.baizhi.cmfz.service;

import com.baizhi.cmfz.dao.RotationDao;
import com.baizhi.cmfz.entity.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Service
public class RotationServiceImpl implements RotationService {

    @Autowired
    private RotationDao rotationDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Rotation> queryAll() {
        return rotationDao.queryAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Rotation> queryByPage(Integer page, Integer rows) {
        Integer start = (page - 1) * rows;
        System.out.println("start = " + start);
        System.out.println("rows = " + rows);
        return rotationDao.findAllDeptByPage(start, rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer selectByCount() {
        return rotationDao.findByCount();
    }

    @Override
    public void add(Rotation rotation) {
        rotationDao.add(rotation);
    }

    @Override
    public void update(Rotation rotation) {
        rotation.setCreateTime(new Date());
        if (rotation.getPhoto() == null) {

        }
        rotationDao.update(rotation);
    }

    @Override
    public void deleteById(String id) {
        rotationDao.deleteById(id);
    }

    @Override
    public void updateById(String id, String photo) {
        rotationDao.updateById(id, photo);
    }

    @Override
    public void deleteByCount(String[] id) {
        rotationDao.deleteCount(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Rotation queryById(String id) {
        return rotationDao.queryById(id);
    }
}
