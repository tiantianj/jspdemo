package dao;

import entity.Subject;

import java.util.List;

/**
 * 定义课程表所有的数据库操作
 */
public interface SubjectDao {
    /**
     * 新增课程
     * @param subject
     */
    void save(Subject subject);

    /**
     * 修改课程
     * @param subject
     */
    void update(Subject subject);

    /**
     * 根据ID删除课程
     * @param no
     */
    void deleteById(int no);

    /**
     * 根据Id查询课程
     * @param no
     * @return
     */
    Subject getById(int no);

    /**
     * 查询所有课程
     * @return
     */
    List<Subject> findAll();

    /**
     * 分页查询课程
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Subject> findByPage(int pageNo, int pageSize);

    /**
     * 分页查询课程（包含关联年级信息）
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Subject> findByPageWithGrade(int pageNo, int pageSize);

    /**
     * 根据课程名称模糊查询分页课程
     * @param likeName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Subject> findByPage(String likeName,int pageNo, int pageSize);

    /**
     * 根据课程名称模糊查询分页课程（包含关联年级信息）
     * @param likeName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Subject> findByPageWithGrade(String likeName,int pageNo, int pageSize);

    /**
     * 获取课程总数量
     * @return
     */
    long getAllCount();

    /**
     * 根据课程名称模糊查询总条数
     * @param likeName
     * @return
     */
    long getAllCount(String likeName);

}
