package com.boot.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig  extends WebSecurityConfigurerAdapter{
	
	@Bean
	public  UserDetailsService getuserdetailservice()
	{
		
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordencoder()
	{
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationprovider()
	{
		DaoAuthenticationProvider daoauthenticationprovider=new DaoAuthenticationProvider();
		daoauthenticationprovider.setUserDetailsService(this.getuserdetailservice());
		daoauthenticationprovider.setPasswordEncoder(passwordencoder());
		
		return daoauthenticationprovider;
	}

	// configure method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationprovider());
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/index")
//		.failureUrl("/login_fail")
		.and().csrf().disable();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//		.antMatchers("/user/**").hasRole("USER")
//		.antMatchers("/**").permitAll().and().formLogin()
//		.loginPage("/signin")
//		.loginProcessingUrl("/dologin")
//		.defaultSuccessUrl("/user/index")
//		.and().csrf().disable();
//	}
	
	

}