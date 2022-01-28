package com.springboot.practice.core.controllers;

import com.springboot.practice.core.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ThymeLeafController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/renderDynamicData")
    public ModelAndView renderDynamicData() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Render Dynamic Data Using Thymeleaf");
        return modelAndView;

    }
    @RequestMapping("/renderObjectData")
    public ModelAndView displayStudentData() {
        ModelAndView modelAndView = new ModelAndView("displayStudent");
        Student student=new Student();
        student.setId(10);
        student.setName("ABC");
        modelAndView.addObject("student", student);
        return modelAndView;

    }

    @RequestMapping("/renderListData")
    public ModelAndView displayStudentsData() {
        ModelAndView modelAndView = new ModelAndView("displayStudents");
        Student student=new Student();
        student.setId(10);
        student.setName("ABC");

        Student student2=new Student();
        student2.setId(20);
        student2.setName("BCD");

        Student student3=new Student();
        student3.setId(30);
        student3.setName("DDD");
        List<Student>students=Arrays.asList(student,student2,student3);
        modelAndView.addObject("students", students);
        return modelAndView;

    }

    @RequestMapping("/displayStudentForm")
    public ModelAndView processStudentFormData() {
        ModelAndView modelAndView = new ModelAndView("studentForm");
        Student student=new Student();
        student.setId(10);
        student.setName("ABC");
        modelAndView.addObject("student", student);
        return modelAndView;

    }

    @RequestMapping("/saveStudentFormData")
    public ModelAndView saveStudentFormData(@ModelAttribute Student student) {
        ModelAndView modelAndView = new ModelAndView("result");

        System.out.println("student name:"+student.getName());
        System.out.println("student id:"+student.getId());

        return modelAndView;

    }
}
