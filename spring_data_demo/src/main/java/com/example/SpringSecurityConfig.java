package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.example.util.Role;

@Configuration
@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	LoginSuccessHandler loginSuccessHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImpl);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); // #3
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic().disable();

		http.authorizeRequests().antMatchers("/", "/login", "/registration.htm").permitAll() 
		.antMatchers("/employer*.htm").access("hasRole('"+Role.employer.name()+"')")
		.antMatchers("/candidate*.htm").access("hasRole('"+Role.candidate.name()+"')")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").
		failureUrl("/login?error=true").
		successHandler(loginSuccessHandler)
		.permitAll();
	}
}