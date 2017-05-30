package com.deliver.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.alibaba.fastjson.JSONObject;
import com.deliver.model.Package;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.stereotype.Service;

/**
 * Created by deadoggy on 17-5-14.
 */

@Service
/*singleton*/
public class ShortMesgService {

    private static Object mLock = new Object();

    private static String mConfig = "message_key";

    private static ShortMesgService singleton = null;

    private String mAppKey = null;

    private String mAppSecret = null;

    private String mRequestURL = null;

    private String mSandBoxRequestURL = null;

    public static ShortMesgService getInstance() throws Exception{

        if(null != singleton){
            return singleton;
        }else{
            synchronized(mLock){
                singleton = new ShortMesgService();
            }
            return singleton;
        }

    }

    private void init() throws Exception{
        try{
            String url = this.getClass().getResource("/").toURI().toString() + "../../static/" + ShortMesgService.mConfig;

            url = url.substring(5);

            File config = new File(url);

            BufferedReader reader = new BufferedReader(new FileReader(config));

            this.mAppKey = reader.readLine();

            this.mAppSecret = reader.readLine();

            this.mRequestURL = reader.readLine();

            this.mSandBoxRequestURL = reader.readLine();

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

    public synchronized boolean SendMessage(Package p, boolean sandbox){
        //TODO: 发送信息
        try{

            String tele = p.getmReceiverTele();

            //TODO: 获取快递点的信息
            String point = "同济大学";

            //TODO: 根据快递信息生成取件码
            String takenCode = "test_code";

            String url = null;

            if(sandbox){
                url = this.mSandBoxRequestURL;
            }else{
                url = this.mRequestURL;
            }

            TaobaoClient client = new DefaultTaobaoClient(url, this.mAppKey, this.mAppSecret);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

            req.setExtend("");

            req.setSmsFreeSignName("快递");
            req.setSmsTemplateCode("SMS_67320391");

            JSONObject para = new JSONObject();
            para.put("point", point);
            para.put("taken_code", takenCode);

            req.setSmsParamString(para.toJSONString());

            req.setSmsType("normal");
            req.setRecNum(tele);

            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);

            System.out.println(rsp.getBody());

            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
