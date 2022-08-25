///**
// * 
// */
//package com.zek.doorstep.configs;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author EK
// *
// */
//@Configuration
//public class ResourceWebConfig implements WebMvcConfigurer {
//  final Environment environment;
//
//  public ResourceWebConfig(Environment environment) {
//    this.environment = environment;
//  }
//
////  @Value("${file.down-dir}") 
//  String downDir ="uploads";
//  
//  @Override
//  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//    String location = environment.getProperty(downDir);
//
//    registry.addResourceHandler("/download/**").addResourceLocations(location);
//  }
//}