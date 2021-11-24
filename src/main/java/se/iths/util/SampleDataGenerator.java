package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData(){
        Student student1 = new Student("Harry", "Potter", "harry@hogwarts.com", "555-555" );
        Student student2 = new Student("Hermione", "Granger", "hermione@hogwarts.com", "555-555" );
        Student student3 = new Student("Ron", "Weasley", "ron@hogwarts.com", "555-555" );
        Student student4 = new Student("Ginny", "Weasley", "ginny@hogwarts.com", "555-555" );
        Student student5 = new Student("Fred", "Weasley", "harry@hogwarts.com", "555-555" );
        Student student6 = new Student("George", "Weasley", "harry@hogwarts.com", "555-555" );
        Student student7 = new Student("Cho", "Chang", "harry@hogwarts.com", "555-555" );
        Student student8 = new Student("Draco", "Malfoy", "harry@hogwarts.com", "555-555" );
        Student student9 = new Student("Katie", "Bell", "harry@hogwarts.com", "555-555" );
        Student student10 = new Student("Luna", "Lovegood", "harry@hogwarts.com", "555-555" );


        Teacher teacher1 = new Teacher("Minerva", "McGonagall", "mcgonagall@hogwarts.com");
        Teacher teacher2 = new Teacher("Rubeus", "Hagrid", "hagrid@hogwarts.com");
        Teacher teacher3 = new Teacher("Severus", "Snape", "snape@hogwarts.com");
        Teacher teacher4 = new Teacher("Filius", "Flitwick", "flitwick@hogwarts.com");
        Teacher teacher5 = new Teacher("Pomona", "Sprout", "sprout@hogwarts.com");


        Subject subject1 = new Subject("Transfiguration");
        Subject subject2 = new Subject("Care of Magical Creatures");
        Subject subject3 = new Subject("Potions");
        Subject subject4 = new Subject("Charms");
        Subject subject5 = new Subject("Herbology");


        teacher1.addSubject(subject1);
        teacher2.addSubject(subject2);
        teacher3.addSubject(subject3);
        teacher4.addSubject(subject4);
        teacher5.addSubject(subject5);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
        entityManager.persist(teacher3);
        entityManager.persist(teacher4);
        entityManager.persist(teacher5);

        student1.addSubject(subject1);
        student1.addSubject(subject2);
        student1.addSubject(subject3);
        student2.addSubject(subject1);
        student2.addSubject(subject2);
        student2.addSubject(subject3);
        student2.addSubject(subject4);
        student2.addSubject(subject5);
        student3.addSubject(subject1);
        student4.addSubject(subject5);
        student5.addSubject(subject4);
        student5.addSubject(subject5);
        student6.addSubject(subject2);
        student6.addSubject(subject4);
        student7.addSubject(subject1);
        student7.addSubject(subject3);
        student7.addSubject(subject5);
        student8.addSubject(subject1);
        student8.addSubject(subject2);
        student9.addSubject(subject1);
        student10.addSubject(subject1);

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
        entityManager.persist(student5);
        entityManager.persist(student6);
        entityManager.persist(student7);
        entityManager.persist(student8);
        entityManager.persist(student9);
        entityManager.persist(student10);


    }

}
