package com.springboot.practice.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.practice.core.batch.csvtodb.config.CSVToDBBatchConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.practice.core.repo.StudentRepository;
import com.springboot.practice.core.service.PaymentServiceImpl;
import com.springboot.practice.core.vo.Student;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootPracticeApplicationTests {

	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	@Autowired
	private StudentRepository repository;
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("stringBatchJob")
	private Job StringBatchJob;

	@Autowired
	@Qualifier("productBatchJob")
	private Job productBatchJob;
	@Test
	public void testDependncyInjection() {
		System.out.println("Hii");
		Assert.assertNotNull(paymentServiceImpl);
		Assert.assertNotNull(paymentServiceImpl.getPaymentDAO());
		System.out.println("Hii");

	}
	@Test
	public void testProductBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
			jobLauncher.run(productBatchJob, jobParameters);

	}

	@Test
	public void testStringBatch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
			jobLauncher.run(StringBatchJob, jobParameters);


	}

	@Test
	public void testStudentRepo() {
		Student student = new Student();
		student.setName("ADI");
		student.setTestscore(100);
		Student persistedStudent=repository.save(student);
		
		List<Student> savedStudents = repository.findAll();
		assertNotNull(savedStudents);
		

		assertNotNull(savedStudents);
		System.out.println(savedStudents.get(0));
		assertEquals(100, savedStudents.get(0).getTestscore());

		repository.delete(student);
		assertTrue(repository.findById(1l).isPresent()==false);


	}

}
