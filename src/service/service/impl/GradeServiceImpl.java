package service.service.impl;

import dao.GradeDao;
import dao.dao.impl.GradeDaoImpl;
import entity.Grade;
import service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {
    private GradeDao dao = new GradeDaoImpl();

    @Override
    public List<Grade> findAll() {
        return dao.findAll();
    }
}
