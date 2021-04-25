package com.labcerebrone.Backend.Microservice;

import com.labcerebrone.Backend.Microservice.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
public class BackendMicroserviceApplication {

	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		ServletRegistrationBean bean = new ServletRegistrationBean(new MyServlet() , "/inc/sendemail.php");
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendMicroserviceApplication.class, args);
	}

}
