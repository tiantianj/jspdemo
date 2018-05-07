package dao;

import entity.Grade;

import java.util.List;

public interface GradeDao {
    /**
     * 查询所有的年级信息
     * @return
     */
    List<Grade> findAll();
}
