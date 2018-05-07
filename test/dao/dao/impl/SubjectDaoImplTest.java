package dao.dao.impl;

import dao.SubjectDao;
import entity.Subject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SubjectDaoImplTest {

    @Test
    public void getAllCount() {
        SubjectDao subjectDao = new SubjectDaoImpl();
        assertEquals(4,subjectDao.getAllCount("数学"));
    }


    @Test
    public void findPage(){
        SubjectDao subjectDao = new SubjectDaoImpl();
        List<Subject> subjects = subjectDao.findByPage(null,1,5);

        System.out.println(subjects.size());
    }
}