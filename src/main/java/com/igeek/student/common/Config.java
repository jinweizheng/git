package com.igeek.student.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class Config {

	@Bean
	public JedisPool getJedisPool() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxWaitMillis(3);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379, 5000, null);
		return jedisPool;
	}
}
