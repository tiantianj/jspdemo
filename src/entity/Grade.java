package entity;

import java.util.Set;

/**
 * 年级
 * @author
 */
public class Grade {
    private Integer id;
    private String name;

    private Set<Subject> subjects;

    public Grade() {
    }

    public Grade(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
