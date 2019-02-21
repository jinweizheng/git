package com.igeek.student.common;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igeek.student.pojo.Dict;
import com.igeek.student.service.DictService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component("proxyDictService")
public class ProxyDictService implements DictService {
	@Autowired
	@Qualifier("dictServiceImpl")
	private DictService  dictService;
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
		List<DictTree> trees=dictService.listDict(dict);
		jedis.set(key, JSON.toJSONString(trees));
		jedis.close();
		return trees;
	}
	@Override
	public int saveDict(Dict dict) {
		// TODO Auto-generated method stub
		String key="Dictkey*";
		redisDelKeys(key);
		dictService.saveDict(dict);
		return dict.getDid();
	}
	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		String key="Dictkey*";
		redisDelKeys(key);
		return dictService.del(id);
	}
	
	protected void redisDelKeys(String likeKey) {
		Jedis jedis =jedisPool.getResource();
		Set<String> keys=jedis.keys(likeKey);
		String[] arr=keys.stream().toArray(String[]::new);
		jedis.del(arr);
		jedis.close();
	}
}
