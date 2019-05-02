package com.oriole.motaserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan("com.oriole.motaserver.dao")
public class MotaserverApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(MotaserverApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        super.addResourceHandlers(registry);
    }
}
