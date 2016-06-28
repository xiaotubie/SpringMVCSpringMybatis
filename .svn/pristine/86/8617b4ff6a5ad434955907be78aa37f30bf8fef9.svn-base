package com.xbwl.demo.service.cache;

import net.sf.ehcache.CacheException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xbwl.demo.utils.SerializeUtil;

@Component
public class RedisCacheService<K, V> {
    Log logger = LogFactory.getLog(getClass());
    /**
     * 缓存过期时间 （单位：S）
     */
    private final int EXPIRE_TIME = 24*60*60;
    
    @Autowired
    private RedisService jedisManager;

    private String chName;

    public RedisCacheService(){
        
    }
    
    /**
     * @param name
     */
    public RedisCacheService(String name){
    	this.chName = name;
    }
    
    /**
     * @return
     */
    public String getCacheName() {
        if (chName == null)
            return "";
        return chName;
    }

    /**
     * @param name
     */
    public void setCacheName(String name) {
        this.chName = name;
    }

    /**
     * @param key
     * @return
     * @throws CacheException
     */
    
    public V get(K key) throws Exception {
        byte[] byteKey = SerializeUtil.getByte(getCacheKey(key));
        byte[] byteValue = jedisManager.get(byteKey);
        return (V) SerializeUtil.unserialize(byteValue);
    }
    

    /**
     * 带有过期时间 的缓存
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    public V putExpire(K key, V value) throws Exception {
        V previos = get(key);
        jedisManager.set(SerializeUtil.getByte(getCacheKey(key)),
                SerializeUtil.serialize(value),
                EXPIRE_TIME);
        return previos;
    }

    /**
     * 无过期时间 的缓存
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    public V put(K key, V value) throws Exception {
        V previos = get(key);
        jedisManager.set(SerializeUtil.getByte(getCacheKey(key)),
                SerializeUtil.serialize(value));
        return previos;
    }
    
    /**
     * @param key
     * @return
     * @throws CacheException
     */
    public V remove(K key) throws Exception {
        V previos = get(key);
        jedisManager.del(SerializeUtil.getByte(getCacheKey(key)));
        return previos;
    }

    /**
     * @throws CacheException
     */
//    public void clear() throws Exception {
//        byte[] keysPattern = SerializeUtil.getByte(getCacheKey("*"));
//    	//String keysPattern = getCacheKey("*");
////        jedisManager.deleteByKeysPattern(keysPattern);
//    }
//    

   

    /**
     * @param key
     * @return
     */
    private String getCacheKey(Object key) {
//        return this.REDIS_CACHE_NAME + getCacheName() + ":" + key;
        return key.toString();
    }
    
}