package com.igeek.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igeek.student.common.DictTree;
import com.igeek.student.mapper.DictMapper;
import com.igeek.student.pojo.Dict;
import com.igeek.student.pojo.DictExample;
import redis.clients.jedis.Jedis;
@Service("dictServiceImpl")
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;


	@Override
	public List<DictTree> listDict(Dict dict) {
		// TODO Auto-generated method stub		
		DictExample dictExample =new DictExample();
		dictExample.createCriteria().andParentIdEqualTo(dict.getParentId());
		
		List<Dict> dicts=dictMapper.selectByExample(dictExample);
		List<DictTree> lTrees=new ArrayList<>();
		for (Dict dict2 : dicts) {
			DictTree dictTree =new DictTree();
			dictTree.setId(dict2.getDid());
			dictTree.setName(dict2.getDname());
			dictTree.setTitle(dict2.getDname());
			dictTree.setIsParent( dictMapper.count(dict2.getDid())>0?true:false);
			dictTree.setParentId(dict2.getParentId());
			lTrees.add(dictTree);
		}
	
		return lTrees;
	}


	@Override
	public int saveDict(Dict dict) {
		// TODO Auto-generated method stub
	
		return 	dictMapper.insert(dict);
	}


	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return dictMapper.deleteByPrimaryKey(id);
	}

	

	

	
  

   
}