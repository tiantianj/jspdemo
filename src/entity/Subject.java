package entity;

/**
 * 课程
 * @author
 */
public class Subject {
    /**
     * 课程编号
     */
    private Integer no;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课时
     */
    private Integer classHour;
    /**
     * 年级编号
     */
    private Integer gradeId;

    /**
     * 关联对象，1个课程对应一个年级
     */
    private Grade grade;

    public Subject() {
    }

    public Subject(String name, Integer classHour, Integer gradeId) {
        this.name = name;
        this.classHour = classHour;
        this.gradeId = gradeId;
    }

    public Subject(Integer no, String name, Integer classHour, Integer gradeId) {
        this.no = no;
        this.name = name;
        this.classHour = classHour;
        this.gradeId = gradeId;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SubjectDao{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", classHour=" + classHour +
                ", gradeId=" + gradeId +
                '}';
    }
}
