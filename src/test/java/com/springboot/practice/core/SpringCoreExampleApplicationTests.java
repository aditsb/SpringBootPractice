package com.springboot.practice.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.practice.core.repo.StudentRepository;
import com.springboot.practice.core.service.PaymentServiceImpl;
import com.springboot.practice.core.vo.Student;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCoreExampleApplicationTests {

	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	@Autowired
	private StudentRepository repository;

	@Test
	public void testDependncyInjection() {
		System.out.println("Hii");
		Assert.assertNotNull(paymentServiceImpl);
		Assert.assertNotNull(paymentServiceImpl.getPaymentDAO());
		System.out.println("Hii");

	}

	/*@Test
	public void testStudentRepo() {
		Student student = new Student();
		student.setName("ADI");
		student.setTestscore(100);
		Student persistedStudent=repository.save(student);
		
		List<Student> savedStudents = repository.findAll();
		assertNotNull(savedStudents);
		

		assertNotNull(savedStudents);
		assertEquals(60, savedStudents.getTestscore());

		repository.delete(student);
		assertTrue(repository.findById(1l).isPresent()==false);


	}*/

}
