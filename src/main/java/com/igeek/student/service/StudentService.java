package com.igeek.student.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.igeek.student.pojo.Student;

public interface StudentService {

	PageInfo<Student> listStudent(Student student,PageInfo<Student> info);


   
}