package com.xbwl.demo.service.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * Redis 服务
 */
@Component
public class RedisService {
	/**
     * 日志
     */
    Log logger = LogFactory.getLog(getClass());
	
	/**
	 * Redis端户端实现
	 */
	private  ShardedJedisPool jedisPool;
    
    /**
     * exist 方法
     * <p>
     * 方法说明
     * </p>
     * 
     * @param key
     * @return
     */
    public boolean exists(byte[] key) {
        ShardedJedis  jedis = jedisPool.getResource();
        boolean isExist = jedis.exists(key);
        jedisPool.returnResourceObject(jedis);//.returnResource(jedis);
        return isExist;
    }

    /**
     * get 方法
     * <p>
     * 方法说明
     * </p>
     * 
     * @param key
     * @return
     */
    @SuppressWarnings("deprecation")
    public byte[] get(byte[] key) {
        ShardedJedis  jedis = jedisPool.getResource();
        byte[] val = jedis.get(key);
        jedisPool.returnResource(jedis);
        return val;
    }
    
    @SuppressWarnings("deprecation")
    public String get(String key) {
        ShardedJedis  jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedisPool.returnResource(jedis);
        return value;
    }
    
    @SuppressWarnings("deprecation")
    public void set(String  key, String value, int expire) {
        if(value == null){
            return;
        }
        
        ShardedJedis  jedis = jedisPool.getResource();
        jedis.set(key, value);
        // 设置过期时间
        jedis.expire(key, expire);
        // 释放连接
        jedisPool.returnResource(jedis);
    }

    /**
     * 
     * <p>
     * 向Redis中保存数据
     * </p>
     * publishExpire 方法
     * <p>
     * 方法说明
     * </p>
     * 
     * @param key
     * @param value
     * @param expire
     *            过期时间(单位 s)
     */
    public void set(byte[] key, byte[] value, int expire) {
        if(value == null){
            return;
        }
        
        ShardedJedis  jedis = jedisPool.getResource();
        //
        jedis.set(key, value);
        // 设置过期时间
        jedis.expire(key, expire);
        // 释放连接
        jedisPool.returnResource(jedis);
    }
    
    /**
     * set 方法
     * <p>方法说明</p>
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        if(value == null){
            return;
        }
        
        logger.debug("key="+key+" value="+value);
        
        ShardedJedis  jedis = jedisPool.getResource();
        
        jedis.set(key, value);
        // 释放连接
        jedisPool.returnResource(jedis);
    }
    
    /**
     * setnx 方法
     * <p>此方法尽量不要用，在多线程环境下，该操作jedis.setnx并不是原子操作</p>
     * @param key
     * @param value
     */
    public void setnx(String key,String value) {
        if(value == null){
            return;
        }
        
        ShardedJedis  jedis = jedisPool.getResource();
        //
        jedis.setnx(key, value);
        // 释放连接
        jedisPool.returnResource(jedis);
    }
    
    
    /**
     * del 方法
     * <p>
     * 方法说明
     * </p>
     * 
     * @param key
     */
    public void del(String key) {
        ShardedJedis  jedis = jedisPool.getResource();
        //
        jedis.del(key);
        // 释放连接
        jedisPool.returnResource(jedis);
    }
    
    /**
     * del 方法
     * <p>
     * 方法说明
     * </p>
     * 
     * @param key
     */
    public void del(byte[] key) {
        ShardedJedis  jedis = jedisPool.getResource();
        //
        jedis.del(key);
        // 释放连接
        jedisPool.returnResource(jedis);
    }
    
    @Autowired
    public void setJedisPool(ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


}
