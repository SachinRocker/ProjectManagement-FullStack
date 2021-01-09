package com.simpleUI.projectmanagementtool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.simpleUI.projectmanagementtool.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,//--> enables the @Secured annotation for the user based roles
		jsr250Enabled = true,//--> enable @RoleAllowed annotation 
		prePostEnabled = true //--> enables @PreAuthorization and @PostAuthorization annotations
		)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	JWTAuthenticationEntryPoint unAuthorizedHandler;
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	@Bean
	 JWTAuthenticationFilter jwtAuthenticationFilter() {return new JWTAuthenticationFilter();}
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder);
		
	}
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors().and().csrf().disable() //disable cross-site-request-forgery as we are using JWT for auth
		.exceptionHandling().authenticationEntryPoint(unAuthorizedHandler).and() //To handle the auth exception
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // To keep the server session state less
		.and()
		.headers().frameOptions().sameOrigin() // for H2 database
		.and()
		.authorizeRequests() //To authorize routing requests
		.antMatchers("/",
				"/favicon.ico",
				"/**/*.png",
				"/**/*.gif",
				"/**/*.svg",
				"/**/*.jpg",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js")
		.permitAll() //--> permit requests based on the above endpoints for all the users
		.antMatchers(SecurityConstants.SIGN_UP_URLS).permitAll()
		.antMatchers(SecurityConstants.H2_URL).permitAll()
		.anyRequest().authenticated();//-> any requests other than the above requests need to be authenticated
		
	http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
}
