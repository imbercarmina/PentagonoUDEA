package com.pentagono.pentagono.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email,password,estado from employee where email=?")
                .authoritiesByUsernameQuery("select email, rol from employee where email=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","see_enterprise/**").hasRole("ADMIN")
                .antMatchers("/see_employee/**").hasRole("ADMIN")
                .antMatchers("/new_enterprise/**").hasRole("ADMIN")
                .antMatchers("/new_employee/**").hasRole("ADMIN")
                .antMatchers("/see_transaction/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/new_transaction/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/edit_transaction/**").hasAnyRole("ADMIN","USER")
                .and().formLogin().successHandler(customSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/accesDenied")
                .and().logout().permitAll();
    }
}
