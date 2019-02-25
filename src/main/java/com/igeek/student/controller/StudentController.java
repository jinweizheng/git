package com.igeek.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.SynchronossPartHttpMessageReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.student.pojo.Student;
import com.igeek.student.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/student")
	@ResponseBody
	public PageInfo<Student> listStudent(Student student,PageInfo<Student> info) {
	
	
		return studentService.listStudent(student,info);
		
	}
	@DeleteMapping("/student/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
	
		return "OK";
		
	}
	
	
	@PutMapping("/student")
	@ResponseBody
	public String put(Student student) {

		System.out.println(student.toString());
		return "put OK";
		
	}
}
