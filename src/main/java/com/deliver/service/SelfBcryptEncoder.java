package com.deliver.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by deadoggy on 17-5-11.
 */
public class SelfBcryptEncoder {

    public String encipher(String username, String pwd){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
        return encoder.encode(retBuilder.subSequence(0,retBuilder.length()));
    }



}
