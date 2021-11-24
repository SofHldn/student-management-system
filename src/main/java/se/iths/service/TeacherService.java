package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {
    @PersistenceContext
    EntityManager entityManager;

    public Teacher createTeacher(Teacher teacher){
//        teacher.addSubject(new Subject("Matte"));
//        teacher.addSubject(new Subject("Musik"));
//        teacher.addSubject(new Subject("Svenska"));
        entityManager.persist(teacher);
        return teacher;
    }

    public List<Teacher> listAllTeachers(){
        return entityManager.createQuery("SELECT t from Teacher t", Teacher.class).getResultList();
    }


}
