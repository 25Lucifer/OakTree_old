package com.tree.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author tree25
 * @date 2021/3/5 10:32 上午
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据 key 获取 object 对象
     * @author tree25
     * @date 2021/3/5 10:35 上午
     * @param key
     * @return Object
     */
    public Object get(final String key){
        if(key.isBlank()){
            return null;
        }
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 写入数据
     * @author tree25
     * @date 2021/3/5 10:44 上午
     * @param key String
     * @Param value Object
     * @return boolean
     */
    public boolean set(final String key,Object value){
        if(key.isBlank()){
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            logger.info("存入 redis 成功，key:{},value:{}",key,value);
        } catch (Exception e) {
            logger.error("存入 redis 失败，key:{},value:{}",key,value);
            e.printStackTrace();
        }
        return false;
    }

}
