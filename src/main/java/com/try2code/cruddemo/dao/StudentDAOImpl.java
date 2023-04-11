package com.try2code.cruddemo.dao;

import com.try2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    //define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class , id);
    }

    @Override
    public List<Student> findAll() {

        //create the Query
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student" , Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName asc" , Student.class);

        // return result
        return theQuery.getResultList() ;
    }

    @Override
    public List<Student> findByLastName(String theLastName) {

        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData" , Student.class);
        //set query parameter
        theQuery.setParameter("theData" , theLastName);
        //return query result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve Student
        Student theStudent = entityManager.find(Student.class , id);
        //delete
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numOfStudentDeleted  = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numOfStudentDeleted;
    }
}
