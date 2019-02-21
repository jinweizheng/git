
package com.igeek.student.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igeek.student.service.StudentService;
@Controller
public class IndexController {
	@Autowired
	private StudentService studentService;

	@RequestMapping(value= {"/{index}"})
	public String index(@PathVariable String index) {
		
	
		return index;
	}
	


}
