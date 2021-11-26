package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String className;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjectList")
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
    }

    public Subject() {
    }

    public Subject(String className) {
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

//    @JsonbTransient
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

//    @JsonbTransient
    public List<Student> getStudents() {
        return students;
    }


}
