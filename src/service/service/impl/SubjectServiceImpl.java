package service.service.impl;

import dao.SubjectDao;
import dao.dao.impl.SubjectDaoImpl;
import entity.Subject;
import service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private SubjectDao dao = new SubjectDaoImpl();

    @Override
    public List<Subject> findPage(String likeName, int pageNo, int pageSize,boolean includeGrade) {
        if(!includeGrade) {
            //如果likeName为null，则查询所有的数据
            if (likeName == null) {
                return dao.findByPage(pageNo, pageSize);
            }
            return dao.findByPage(likeName, pageNo, pageSize);
        }else{
            //如果likeName为null，则查询所有的数据
            if (likeName == null) {
                return dao.findByPageWithGrade(pageNo, pageSize);
            }
            return dao.findByPageWithGrade(likeName, pageNo, pageSize);
        }
    }

    @Override
    public int getCount(String likeName) {
        if(likeName==null){
            return (int)dao.getAllCount();
        }
        return (int)dao.getAllCount(likeName);
    }
}
