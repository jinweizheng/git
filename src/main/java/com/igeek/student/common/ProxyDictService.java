package com.igeek.student.common;


import java.util.List;

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
			System.out.println("redis 取出数据");

			jedis.close();
			return JSONObject.parseArray(str, DictTree.class);
		}
		List<DictTree> trees=dictService.listDict(dict);
		jedis.set(key, JSON.toJSONString(trees));
		jedis.close();
		return trees;
	}
	
}
