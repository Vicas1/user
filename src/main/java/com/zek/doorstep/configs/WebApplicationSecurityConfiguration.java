package com.zek.doorstep.configs;


import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zek.doorstep.util.enums.Gender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class WebApplicationSecurityConfiguration  extends WebSecurityConfigurerAdapter  {
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	//authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(this.userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
//	 @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }
// 
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//         return authenticationConfiguration.getAuthenticationManager();
//     }
// 

 
		/*
		 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
		 * Exception { http .authorizeHttpRequests((authz) -> authz
		 * .anyRequest().authenticated() ) .httpBasic(withDefaults()); return
		 * http.build(); }
		 */
     
//     @Bean
//     public WebSecurityCustomizer webSecurityCustomizer() {
//         return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//     }
//     
		/*
		 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
		 * Exception { http.cors().and().csrf().disable()
		 * .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		 * and() .authorizeRequests() .antMatchers(UrlMapping.AUTH +
		 * UrlMapping.SIGN_UP).permitAll() .antMatchers(UrlMapping.AUTH +
		 * UrlMapping.LOGIN).permitAll()
		 * .antMatchers(UrlMapping.VALIDATE_JWT).permitAll()
		 * .antMatchers("/api/test/**").permitAll() .anyRequest().authenticated();
		 * 
		 * http.addFilterBefore(authenticationJwtTokenFilter,
		 * UsernamePasswordAuthenticationFilter.class);
		 * 
		 * return http.build(); }
		 */
 
//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**")
//                         .allowedMethods("*");
//             }
//         };
//     }
     
	//authorization
	@Override
	protected void configure(HttpSecurity http)  throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		http.authorizeRequests()
		.antMatchers("/login**", "/logout**", "/h2-console/**")
			.permitAll();
//		.antMatchers(HttpMethod.GET, "/user**", "/**")
//			.hasAnyRole("ADMIN")		.antMatchers(HttpMethod.POST, "/api/v1/orders**")
//			.hasRole("USER")
//		.antMatchers(HttpMethod.DELETE, "/api/v1/orders/**")
//			.hasRole("USER")
//		.and()
//			.httpBasic()
//			.and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}

