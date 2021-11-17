package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;


    //CREATE
    public Student createStudent(Student student){
        entityManager.persist(student);
        return student;
    }

    //READ
    public Student findStudentById (Long id){
        return entityManager.find(Student.class, id);
    }
    public List<Student> findStudentByLastName (String lastName){
        return entityManager.createQuery("select s from Student s where s.lastName like '" +lastName + "'", Student.class).getResultList();
    }

    public List<Student> listAllStudents(){
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    //UPDATE
    public void updateStudent(Student student){
        entityManager.merge(student);
    }

    public Student updateLastName (Long id, String lastName){
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(lastName);
        return foundStudent;
    }

    //DELETE
    public void deleteStudent(Long id){
        Student studentToRemove = entityManager.find(Student.class, id);
        entityManager.remove(studentToRemove);

    }


}
