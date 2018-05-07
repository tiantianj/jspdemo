package dao.dao.impl;

import dao.BaseDao;
import dao.GradeDao;
import entity.Grade;
import entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends BaseDao implements GradeDao {
    @Override
    public List<Grade> findAll() {
        String sql = "SELECT gradeid,gradeName FROM grade";
        return getSubjectByRS(executeQuery(sql));
    }

    private List<Grade> getSubjectByRS(ResultSet rs){
        List<Grade> grades = null;
        if(rs!=null){
            grades = new ArrayList<>();
            try {
                while (rs.next()){
                    int no = rs.getInt("gradeId");
                    String name = rs.getString("gradeName");
                    Grade grade = new Grade(no,name);
                    grades.add(grade);
                }
            }catch (SQLException e){
                e.printStackTrace();
            } finally {
                closeAll();
            }
        }
        return grades;
    }
}
