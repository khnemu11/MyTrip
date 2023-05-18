package com.kiki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = {"com.kiki.**.dao"})
public class MytripApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MytripApplication.class, args);
	}

	// 정적 파일 경로 추가
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/assets/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/assets/js/");
	    registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/assets/img/");
	}
	// 테스트 주석
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	
}
