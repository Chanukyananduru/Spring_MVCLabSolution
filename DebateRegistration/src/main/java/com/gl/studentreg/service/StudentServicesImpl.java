	package com.gl.studentreg.service;

import com.gl.studentreg.entity.Student;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentServicesImpl implements StudentServices {

	private SessionFactory sessionFactory;
	private Session session;

	// Constructor to start sessionFactory
	@Autowired
	StudentServicesImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Student> findAll() {

		Transaction trans = session.beginTransaction();

		List<Student> students = session.createQuery("from Student",Student.class).list();

		trans.commit();

		return students;
	}

	@Transactional
	public Student findById(int Id) {
		Student student = new Student();

		Transaction trans = session.beginTransaction();

		student = session.get(Student.class, Id);

		trans.commit();

		return student;
	}

	@Transactional
	public void save(Student individual) {
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(individual);
		trans.commit();

	}

	@Override
	public void deleteById(int Id) {
		Student student = new Student();
		
		Transaction trans = session.beginTransaction();

		student = session.get(Student.class, Id);
		
		session.delete(student);
		trans.commit();

	}

}
