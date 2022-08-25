package com.zek.doorstep.configs;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "app", value = "loadUser", havingValue = "true", matchIfMissing = true)
	public User userBasedOnProperty() {
		return new User();
	}

	@Bean
	@ConditionalOnBean(name = "userBasedOnProperty")
	public User userBasedOnBean() {
		return new User();
	}

	@Bean
	@ConditionalOnMissingBean(name = "userBasedOnProperty")
	public User userBasedOnMissingBean() {
		return new User();
	}

	@Bean
	@ConditionalOnMissingClass(value = "com.xyz.test.InvalidClass")
	public User userBasedOnMissingClass() {
		return new User();
	}

	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "PUT", "POST", "DELETE");
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}

class User {

}
