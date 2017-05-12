package com.deliver.service;

import com.deliver.dao.EmployeeRepository;
import com.deliver.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by deadoggy on 17-5-10.
 */

public class SelfUserDetailsServiceImpl implements UserDetailsService {


    private EmployeeRepository repo;

    public SelfUserDetailsServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Employee user = repo.findByMEmployeeId(username);
            if(null == user){
                throw new UsernameNotFoundException("invalid user: " + username);
            }

            //添加角色
            ArrayList<GrantedAuthority> authentications = new ArrayList<>();
            authentications.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(
                    user.getmEmployeeId(),
                    user.getmPwd(),
                    authentications
            );
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

}
