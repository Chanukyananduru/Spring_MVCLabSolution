package com.gl.studentreg.controller;

import java.util.List;

import com.gl.studentreg.entity.Student;
import com.gl.studentreg.service.StudentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServices studentservice;

	@RequestMapping("/list") //"/list"
	public String listStudents(Model theModel) {

		System.out.println("Request to list all students received");
		List<Student> allStudents = studentservice.findAll();
		theModel.addAttribute("Students", allStudents);

		return "list-students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Student individual = new Student();
		theModel.addAttribute("Student", individual);

		return "student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int Id, Model theModel) {
		Student individual = studentservice.findById(Id);
		theModel.addAttribute("Student", individual);
		return "student-form";

	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int Id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("department") String department,
			@RequestParam("country") String country) {

		System.out.println(Id);
		Student individual;
		if (Id != 0) {
			individual = studentservice.findById(Id);
			individual.setFirstName(firstName);
			individual.setLastName(lastName);
			individual.setDepartment(department);
			individual.setCountry(country);
		} else
			individual = new Student(firstName, lastName, department, country);

		studentservice.save(individual);

		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int Id) {
		studentservice.deleteById(Id);
		return "redirect:/student/list";

	}

}
