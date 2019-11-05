package com.proyecto.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		/*
		http.authorizeRequests().antMatchers("/rest/**").authenticated().anyRequest().permitAll().and()
		.authorizeRequests().antMatchers("/cliente/**","/xd/**").authenticated().anyRequest().permitAll();
		*/
		
		/*
		//hasAnyRole("ADMIN").anyRequest().authenticated()
		http.authorizeRequests()
		.antMatchers(resources).permitAll()
		.antMatchers("/login","/register").permitAll()
		.antMatchers("/cliente/**", "/producto/**","/index/**","/demo/**","/xd/**","/cotizacion/**","/admin/**").hasAnyRole("ADMIN").anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.defaultSuccessUrl("/index")
		.failureUrl("/login?error=true")
		.usernameParameter("username")
		.passwordParameter("password")
		.and()
		.logout()
		.permitAll()
		.logoutSuccessUrl("/login?logout");
		*/
		
		
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/admin/**","/cliente/**","/producto/**","/venta/**").
		fullyAuthenticated().and().httpBasic();
		
		
	}
	
	/*@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/resources/**","/static/**", "/css/**","/js/**","/images/**","/Bootstrap/**");
	}
	*/
	
	String[] resources = new String[]{
            "/include/**","/css/**","/img/**","/js/**","/Bootstrap/**","/static/**","/Bootstrap/css/**","/Bootstrap/js/**","/Bootstrap/scss/**","/Bootstrap/vendedor/**"            
	};
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		
		return new BCryptPasswordEncoder(); 
	}
	
}
