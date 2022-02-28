package com.springboot.practice.core.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.springboot.practice.core.repo.StudentRepository;
import com.springboot.practice.core.service.VaccineService;
import com.springboot.practice.core.vo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StudentRestControllerTest {

    private static final String STUDENTS_API_BASE_URL = "/studentsapi/students/";
    private static final String CONTEXT_PATH = "/studentsapi";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository mockStudentRepository;

    @MockBean
    private VaccineService mockVaccineService;

    private
    ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void testFindAllStudents() throws Exception {
        Student student = getStudent();
        List<Student> students = Arrays.asList(student);
        Mockito.when(mockStudentRepository.findAll()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders.get(STUDENTS_API_BASE_URL).contextPath(CONTEXT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(objectWriter.writeValueAsString(students)));

    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = getStudent();
        Mockito.when(mockStudentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post(STUDENTS_API_BASE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(student)).contextPath(CONTEXT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(objectWriter.writeValueAsString(student)));

    }

    @Test
    public void testGetStudent() throws Exception {
        Student student = getStudent();
        Optional optional=Optional.of(student);
        Mockito.when(mockStudentRepository.findById(Mockito.any(Long.class))).thenReturn(optional);
        mockMvc.perform(MockMvcRequestBuilders.get(STUDENTS_API_BASE_URL+100).contextPath(CONTEXT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(objectWriter.writeValueAsString(student)));

    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = getStudent();
        Optional optional=Optional.of(student);
        Mockito.doNothing().when(mockStudentRepository).deleteById(Mockito.any(Long.class));
        mockMvc.perform(MockMvcRequestBuilders.delete(STUDENTS_API_BASE_URL+100).contextPath(CONTEXT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = getStudent();
        Mockito.when(mockStudentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.put(STUDENTS_API_BASE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(student)).contextPath(CONTEXT_PATH))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(objectWriter.writeValueAsString(student)));

    }

    private Student getStudent() {
        Student student = new Student();
        student.setId(100);
        student.setName("ADI");
        student.setTestscore(100);
        return student;
    }

}