package com.deliver.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

/**
 * Created by deadoggy on 17-5-11.
 */
public class SelfAuthenticationProviderImpl implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private SelfBcryptEncoder bcryptEncoder;

    public SelfAuthenticationProviderImpl(UserDetailsService uDS){
        this.userDetailsService = uDS;
        this.bcryptEncoder = new SelfBcryptEncoder();
    }

    public Authentication authenticate(Authentication auth) throws AuthenticationException{
        try{
            String inputUsername = auth.getName();
            String inputPwd = auth.getCredentials().toString();

            //如果找不到会抛出异常
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(inputUsername);


            if(0 == userDetails.getPassword().compareTo(this.bcryptEncoder.encipher(inputUsername, inputPwd))){
                return new UsernamePasswordAuthenticationToken(inputUsername, inputPwd, userDetails.getAuthorities());
            }

            throw new BadCredentialsException("bad credential");


        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean supports(Class<?> var1){
        return true;
    }


}
