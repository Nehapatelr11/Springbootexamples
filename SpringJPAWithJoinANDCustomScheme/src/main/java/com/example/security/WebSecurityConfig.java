package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
				.antMatchers("/","/home").permitAll()
				.antMatchers("/*/edit/*").access("hasRole('ADMIN')")
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/accessdenied")
				.and()
				.logout()
				.permitAll();        
		
			System.out.println("This is test content");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("neha").password("admin123").roles("USER");
		auth.inMemoryAuthentication().withUser("rishit").password("admin123").roles("ADMIN");
	}
	
}
