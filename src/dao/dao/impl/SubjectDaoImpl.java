package dao.dao.impl;

import dao.BaseDao;
import dao.SubjectDao;
import entity.Grade;
import entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 课程相关的数据库操作
 * @author
 *
 * 一般情况下，单张表的数据库操作一般包含
 * 1.保存对象
 * 2.修改对象
 * 3.根据ID删除对象
 * 4.根据ID查询对象
 * 5.分页查询对象（可能还包含其它查询条件）
 * 6.查询所有数据的条数
 */
public class SubjectDaoImpl extends BaseDao implements SubjectDao {
    private String baseSql = "SELECT subjectNo,subjectName,classHour,GradeId FROM subject ";


    @Override
    public void save(Subject subject) {
        String sql = "INSERT INTO subject(subjectName,classhour,gradeid) VALUES(?,?,?)";
        Object[] paras = {subject.getName(),subject.getClassHour(),subject.getGradeId()};
        if(executeUpdate(sql,paras)>0){
            System.out.println("新增"+subject+"成功");
        }else{
            System.out.println("新增"+subject+"失败");
        }
    }

    @Override
    public void update(Subject subject) {
        String sql = "UPDATE subject SET subjectName=?,classHour=?,gradeId=? WHERE subjectNo=?";
        Object[] paras = {subject.getName(),subject.getClassHour(),subject.getGradeId(),subject.getNo()};
        executeUpdate(sql,paras);
    }

    /**
     * 根据ID删除课程
     * @param id
     */
    @Override
    public void deleteById(int id){
        String sql = "DELETE FROM subject WHERE subjectNo=?";
        int flag = executeUpdate(sql,id);
        if(flag>0){
            System.out.println("删除课程"+id+"成功");
        }else{
            System.out.println("删除课程"+id+"失败");
        }
    }

    /**
     *
     * 查询所有课程
     * @return  所有课程的集合
     */
    @Override
    public List<Subject> findAll(){
        return getSubjectByRS(executeQuery(baseSql));
    }

    /**
     * 分页查询课程
     * @param pageNo 页码编号
     * @param pageSize 每页显示数量
     * @return
     * 注意：一般情况下，系统不会一次性查询出表中所有的数据，而是使用分页查询
     * 分页查询提供的参数：第几页pageNo,每页显示数量pageSize
     * 分页查询需要使用的参数（LIMIT offset,pageNo）:offset = (pageNo-1)*pageSize
     */
    @Override
    public List<Subject> findByPage(int pageNo,int pageSize){
        String sql = baseSql+"LIMIT ?,? ";
        int offset = (pageNo-1)*pageSize;
        return getSubjectByRS(executeQuery(sql,offset,pageSize));
    }

    @Override
    public List<Subject> findByPageWithGrade(int pageNo, int pageSize) {
        String sql = "SELECT subjectNo,subjectName,classHour,s.GradeId,gradeName FROM subject s left join grade g " +
                "on s.gradeId=g.gradeid LIMIT ?,?";
        int offset = (pageNo-1)*pageSize;
        ResultSet rs = executeQuery(sql,offset,pageSize);
        List<Subject> subjects = new ArrayList<>();
        try {
            while (rs.next()) {
                int no = rs.getInt("subjectNo");
                String name = rs.getString("subjectName");
                int classHour = rs.getInt("classHour");
                int gradeId = rs.getInt("gradeId");
                String gradeName = rs.getString("gradeName");
                Subject subject = new Subject(no,name,classHour,gradeId);
                Grade grade = new Grade(gradeId,gradeName);
                //构建两个对象的关联关系
                subject.setGrade(grade);
                subjects.add(subject);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return subjects;
    }

    @Override
    public List<Subject> findByPage(String likeName, int pageNo, int pageSize) {
        String sql = baseSql + " WHERE subjectName like ? LIMIT ?,? ";
        int offset = (pageNo-1)*pageSize;
        Object[] params = {"%"+likeName+"%",offset,pageSize};
        return getSubjectByRS(executeQuery(sql,params));
    }


    @Override
    public List<Subject> findByPageWithGrade(String likeName, int pageNo, int pageSize) {
        String sql ="SELECT subjectNo,subjectName,classHour,s.GradeId,gradeName FROM subject s left join grade g " +
                "on s.gradeId=g.gradeid where subjectName LIKE ? LIMIT ?,?";
        int offset = (pageNo-1)*pageSize;
        Object[] params = {"%"+likeName+"%",offset,pageSize};
        ResultSet rs = executeQuery(sql,params);
        List<Subject> subjects = new ArrayList<>();
        try {
            while (rs.next()) {
                int no = rs.getInt("subjectNo");
                String name = rs.getString("subjectName");
                int classHour = rs.getInt("classHour");
                int gradeId = rs.getInt("gradeId");
                String gradeName = rs.getString("gradeName");
                Subject subject = new Subject(no,name,classHour,gradeId);
                Grade grade = new Grade(gradeId,gradeName);
                //构建两个对象的关联关系
                subject.setGrade(grade);
                subjects.add(subject);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return subjects;
    }


    @Override
    public long getAllCount() {
        String sql = "SELECT COUNT(1) FROM subject";
        int count = 0;
        return (Long) executeUnique(sql);
    }



    @Override
    public long getAllCount(String likeName) {
        String sql = "SELECT COUNT(1) FROM subject WHERE subjectName LIKE ?";
        int count = 0;
        return (Long) executeUnique(sql,"%"+likeName+"%");
    }
    /**
     * 根据课程编号查询课程对象
     * @param no
     * @return
     */
    @Override
    public Subject getById(int no){
        String sql = baseSql+" WHERE subjectNo = ? ";
        List<Subject> subs = getSubjectByRS(executeQuery(sql,no));
        if(subs!=null&&subs.size()>0){
            return subs.get(0);
        }
        return null;
    }


    /**
     * 提取从结果集中获取课程集合的方法
     * @param rs
     * @return
     */
    private List<Subject> getSubjectByRS(ResultSet rs){
        List<Subject> subs = null;
        if(rs!=null){
            subs = new ArrayList<>();
            try {
                while (rs.next()){
                    int no = rs.getInt("subjectNo");
                    String name = rs.getString("subjectName");
                    int classHour = rs.getInt("classHour");
                    int gradeId = rs.getInt("gradeId");
                    Subject subject = new Subject(no,name,classHour,gradeId);
                    subs.add(subject);
                }
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                closeAll();
            }
        }
        return subs;
    }
}
