package com.igeek.student.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.igeek.student.common.DictTree;
import com.igeek.student.pojo.Dict;
import com.igeek.student.pojo.Student;
import com.igeek.student.service.DictService;
@Controller
public class DictController {

	@Autowired
	@Qualifier("proxyDictService")
	private DictService dictService;
	
	@RequestMapping("/dict/tree")
	@ResponseBody
	public List<DictTree> index(@RequestParam(defaultValue="0")Integer parentId) {
		
		Dict dict =new Dict();
		dict.setParentId(parentId);
		List<DictTree> treeViews=dictService.listDict(dict);
		return treeViews;	
		
	}
	@PostMapping("/dict")
	@ResponseBody
	public Integer save(Dict dict) {
		dictService.saveDict(dict);
		return dict.getDid();
		
	}
	@PutMapping("/dict")
	@ResponseBody
	public Integer put(Dict dict) {
		
		
		
		return dictService.updateSelective(dict);
		
	}
	@DeleteMapping("/dict/{id}")
	@ResponseBody
	public Integer del(@PathVariable("id") Integer id) {

		return dictService.del(id);
		
	}
}
