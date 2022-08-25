package com.zek.doorstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.zek.doorstep.service.FilesStorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class DoorstepApplication{
	
//@Autowired
private final FilesStorageService filesStorageService;
//@Autowired
//private Environment environment;

//@Autowired WeatherService ws;

	public static void main(String[] args) {
//		SpringApplication.run(DoorstepApplication.class, args);
//		  AnsiConsole.systemInstall();
		 new SpringApplicationBuilder(DoorstepApplication.class)
//       .bannerMode(Banner.Mode.OFF)
       .run(args);
	}
//
//	CommandLineRunner{
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//	}
	
	 @Bean
	    public CommandLineRunner run() throws Exception {
	        return args -> {
	        	log.info("File storage create");
	        	filesStorageService.init();
	        };
	    }
}
