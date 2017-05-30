package com.deliver.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by deadoggy on 17-5-11.
 */
public class SelfBcryptEncoder {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    protected StringBuilder processStr(String username, String pwd){
        StringBuilder retBuilder = new StringBuilder();
        int len = username.length();
        if(len >= 3){
            retBuilder.append(username.substring(0,2));
            retBuilder.append(pwd);
            retBuilder.append(username.substring(2,len));
        }else{
            retBuilder.append(username);
            retBuilder.append(pwd);
        }
        return retBuilder;
    }

    public String encipher(String username, String pwd){

        StringBuilder retBuilder = this.processStr(username,pwd);

        return encoder.encode(retBuilder.subSequence(0,retBuilder.length()));
    }

    public String encipher(String code){
        return encoder.encode(code.subSequence(0, code.length()));
    }

    public boolean match(String username, String rawPwd, String pwd){

        StringBuilder raw = this.processStr(username,rawPwd);

        return encoder.matches(raw, pwd);

    }



}
