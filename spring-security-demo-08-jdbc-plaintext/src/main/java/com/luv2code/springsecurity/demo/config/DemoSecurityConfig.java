package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//add reference to our security data source
	@Autowired
	private DataSource securityDataSource;
	
	
	


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*add our users for the in memory authentication
		 * deprecated method used for educational purposes only 
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("Pepe").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("Marce").password("test123").roles("EMPLOYEE","MANAGER"))
			.withUser(users.username("Tete").password("test123").roles("EMPLOYEE","ADMIN"));
		*/
		
		//use jdbc authentication ----------------------------------------
		auth.jdbcAuthentication().dataSource(securityDataSource);//we now use our database!!!!!
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//.anyRequest().authenticated() we now want to authorize people access depending of their roles
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/admins/**").hasRole("ADMIN")
		.and()
		.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
}//end class
