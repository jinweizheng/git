package com.igeek.student.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.igeek.student.mapper.DictMapper;
import com.igeek.student.mapper.StudentMapper;
import com.igeek.student.pojo.Student;
import com.igeek.student.pojo.StudentExample;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private DictMapper dictMapper;
	@Override
	public List<Student> listStudents(Student student) {
		// TODO Auto-generated method stub
		StudentExample studentExample=new StudentExample();
		Integer did=student.getDid();
		if(did!=null) {
			studentExample.createCriteria().andDidEqualTo(did);
		}
		return studentMapper.selectByExample(studentExample);
	}
	
	
	


}
