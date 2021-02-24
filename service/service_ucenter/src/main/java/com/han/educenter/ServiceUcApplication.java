package com.han.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ComponentScan({"com.han"})
@MapperScan("com.han.educenter.mapper")
@EnableSwagger2
public class ServiceUcApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceUcApplication.class, args);
	}
}