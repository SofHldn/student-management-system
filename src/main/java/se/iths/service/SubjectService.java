package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {
    @PersistenceContext
    EntityManager entityManager;

    public Subject createSubject(Subject subject){
        entityManager.persist(subject);
        return subject;
    }

    public List<Subject> listAllSubjects(){
        List<Subject> subjects = entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();

        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }


}
