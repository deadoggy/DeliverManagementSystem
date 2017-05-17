package com.deliver.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by deadoggy on 17-5-17.
 */

@Service
public class RedisService {

    private static JedisPool pool = new JedisPool();

    public RedisService(){
    }

    public void saveCode(String sessionId, String code){
        Jedis connector = pool.getResource();
        connector.set(sessionId,code.toLowerCase());
    }

    public boolean checkCode(String sessionId, String code){
        try{
            Jedis connector = pool.getResource();
            String testCode=connector.get(sessionId);
            if(null == testCode || 0 != testCode.compareTo(code.toLowerCase())){
                return false;
            }
            connector.del(sessionId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

}
