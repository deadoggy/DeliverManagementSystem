package com.deliver.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by deadoggy on 17-5-17.
 */

@Service
public class RedisService {


    private static Object lock = new Object();
    private static RedisService instance = null;
    private static JedisPool pool = null;
    private static JedisPoolConfig config = null;
    private static String localhost = "127.0.0.1";
    private static String remotehost = "139.129.18.35";

    private String usingHost = null;

    private RedisService(){
        // 池基本配置
        config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);

        try{
            pool = new JedisPool(config,localhost, 6379);
            //测试连接
            Jedis connector = pool.getResource();
            this.usingHost = localhost;
        }catch(Exception e){
            try{//本地redis没有正常启动
                pool = new JedisPool(config, remotehost, 6379, 10000, "deliversystem");
                //测试连接
                Jedis connector = pool.getResource();
                this.usingHost = remotehost;
            }catch(Exception e2){
                e2.printStackTrace();
                throw e2;
            }
        }
    }

    public static RedisService getInstance(){
        if(null != instance){
            return instance;
        }else{
            synchronized (lock){
                instance = new RedisService();
            }
            return instance;
        }
    }



    public void saveCode(String sessionId, String code){
        try(Jedis connector = pool.getResource()){
            connector.set(sessionId,code.toLowerCase());
        }
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
