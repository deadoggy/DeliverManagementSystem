package com.deliver.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import com.deliver.model.Package;

/**
 * Created by deadoggy on 17-5-14.
 */

/*singleton*/
public class ShortMesgService {

    private static String mConfig = "message_key";

    private static ShortMesgService singleton = null;

    private String mAppKey = null;

    private String mAppSecret = null;

    public ShortMesgService getInstance() throws Exception{

        if(null != singleton){
            return singleton;
        }else{
            synchronized(this){
                singleton = new ShortMesgService();
            }
            return singleton;
        }

    }

    private void init() throws Exception{
        try{
            String url = this.getClass().getResource("/").toURI().toString() + "../../../../static/" + ShortMesgService.mConfig;

            File config = new File(url);

            BufferedReader reader = new BufferedReader(new FileReader(config));

            this.mAppKey = reader.readLine();

            this.mAppSecret = reader.readLine();

            reader.close();

        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    private ShortMesgService() throws Exception {
        try{
            this.init();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public synchronized boolean SendMessage(Package p){
        //TODO: 发送信息
        return true;
    }

}
