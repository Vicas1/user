package com.zek.doorstep.configs;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
 
@Component
@Getter
@Setter
@Slf4j
//@RequiredArgsConstructor
public class EnvironmentConfiguration implements EnvironmentAware, WebMvcConfigurer {
 
    private String upload;
    private int maxCount;
//    private final Environment environment;
//    private String upload;
//    private String upload;
//    private String upload;
 
    @Override
    public void setEnvironment(Environment env) {
        this.upload = env.getProperty("spring.file.upload");
        this.maxCount = env.getProperty("spring.file.uploadMax", Integer.class);
    }
 
   
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//      String location = environment.getProperty(this.upload);
    	Path root = Paths.get(upload);
		log.info(root.toString());
		log.info(root.toAbsolutePath().normalize().toString());
      registry.addResourceHandler("/download/**").addResourceLocations(this.upload);
      registry.addResourceHandler("/download1/**").addResourceLocations("file:///"+root.toAbsolutePath().normalize().toString());
    }
}
 
//Another alternative is to simply inject Environment into our controller/bean.
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
// 
//@Component
//public class Details {
// 
//    @Autowired
//    private Environment env;
// 
//    public String getUrl() {
//        return env.getProperty("spring.datasource.url");
//    }
//}