package com.igeek.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igeek.student.common.DictTree;
import com.igeek.student.mapper.DictMapper;
import com.igeek.student.mapper.StudentMapper;
import com.igeek.student.pojo.Dict;
import com.igeek.student.pojo.DictExample;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service()
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private JedisPool  jedisPool;
	@Override
	public List<DictTree> listDict(Dict dict) {
		// TODO Auto-generated method stub
				String key="Dictkey:"+dict.getParentId();
				Jedis jedis =jedisPool.getResource();
				String str=jedis.get(key);
				if (str!=null) {
					jedis.close();
					return JSONObject.parseArray(str, DictTree.class);
				}
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
		jedis.set(key, JSON.toJSONString(lTrees));
		jedis.close();
		return lTrees;
	}
	
	

	@Override
	public int saveDict(Dict dict) {
		// TODO Auto-generated method stub
		String key="Dictkey*";
		redisDelKeys(key);
		return 	dictMapper.insert(dict);
	}


	@Override
	public int del(Integer id) {
		String key="Dictkey*";
		redisDelKeys(key);
		// TODO Auto-generated method stub
		return dictMapper.deleteByPrimaryKey(id);
	}



	@Override
	public int updateSelective(Dict dict) {
		String key="Dictkey*";
		redisDelKeys(key);
		// TODO Auto-generated method stub
		return dictMapper.updateByPrimaryKeySelective(dict);
	}

	

	

	protected void redisDelKeys(String likeKey) {
		Jedis jedis =jedisPool.getResource();
		Set<String> keys=jedis.keys(likeKey);
		if(!keys.isEmpty()) {
			String[] arr=keys.stream().toArray(String[]::new);
			jedis.del(arr);
		}
		jedis.close();
	}
  

   
}