package com.springboot.practice.core.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.practice.core.repo.StudentRepository;
import com.springboot.practice.core.vo.Student;

@RestController
public class StudentRestController {

	@Autowired
	StudentRepository studentRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);

	@RequestMapping(value = "/demo/")
	public String demo() {
		System.out.println("Demo App");
		return "Demo App";
	}


	@RequestMapping(value = "/students/", method = RequestMethod.GET)
	@Cacheable("student-cache")
	@Transactional(readOnly = true)
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable("id") long id) {
		LOGGER.info("Finding Student by ID:"+id);
		return studentRepository.findById(id).isPresent()?studentRepository.findById(id).get():new Student();
	}

	@RequestMapping(value = "/students/", method = RequestMethod.POST)
	public Student createStudent(@RequestBody Student student) {
		LOGGER.info("Student:{}",student);
		return studentRepository.save(student);
	}

	@RequestMapping(value = "/students/", method = RequestMethod.PUT)
	public Student updateStudent(@RequestBody Student Student) {
		return studentRepository.save(Student);
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
	@CacheEvict("student-cache")
	public void deleteStudent(@PathVariable("id") long id) {
		studentRepository.deleteById(id);
	}

}
