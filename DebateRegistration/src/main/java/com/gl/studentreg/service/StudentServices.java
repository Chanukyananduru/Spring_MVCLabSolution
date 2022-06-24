package com.gl.studentreg.service;

import java.util.List;

import com.gl.studentreg.entity.Student;

public interface StudentServices {

	public List<Student> findAll();

	public Student findById(int Id);

	public void save(Student individual);

	public void deleteById(int theId);

}
