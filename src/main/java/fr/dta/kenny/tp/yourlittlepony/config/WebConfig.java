package fr.dta.kenny.tp.yourlittlepony.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import fr.dta.kenny.tp.yourlittlepony.service.UserService;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebConfig(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("123456").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("123456").roles("MANAGER"))
			.withUser(users.username("susan").password("123456").roles("ADMIN"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/pony").hasRole("EMPLOYEE")
		.antMatchers(HttpMethod.GET, "/pony/*").hasRole("EMPLOYEE")
		.antMatchers(HttpMethod.POST, "/pony").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/pony/*").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/pony/*").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.DELETE, "/pony/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/login").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	
}
