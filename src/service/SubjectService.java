package service;

import entity.Subject;

import java.util.List;

/**
 * 关于课程的业务方法
 * @author
 */
public interface SubjectService {
    /**
     * 模糊分页查询
     * @param likeName
     * @param pageNo
     * @param pageSize
     * @param includeGrade 是否查询关联的年级信息
     * @return
     */
    List<Subject> findPage(String likeName,int pageNo,int pageSize,boolean includeGrade);

    /**
     * 获取模糊查询条数
     * @param likeName
     * @return
     */
    int getCount(String likeName);

}
