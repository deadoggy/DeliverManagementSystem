package com.deliver.config;

import com.deliver.dao.EmployeeRepository;
import com.deliver.service.HumanManageService;
import com.deliver.service.SelfAuthenticationProviderImpl;
import com.deliver.service.SelfUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by deadoggy on 17-5-10.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override

    protected void configure(HttpSecurity http) throws Exception {

        //TODO: 认证

        //静态资源通过nginx访问, 其他资源通过tomcat访问
        try{
            http.authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and().csrf().disable();
//                    .antMatchers("/checkCode").permitAll()
//                    .antMatchers("/code").permitAll()
//                    .antMatchers("/static/**").permitAll()
//                    .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home",true)
//                .and().csrf().disable();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{

        SelfUserDetailsServiceImpl userDetailsService = new SelfUserDetailsServiceImpl(this.employeeRepository);

        SelfAuthenticationProviderImpl authenticationProvider = new SelfAuthenticationProviderImpl(userDetailsService);

        auth.authenticationProvider(authenticationProvider).userDetailsService(userDetailsService);
    }



}