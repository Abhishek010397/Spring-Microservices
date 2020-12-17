package com.labcerebrone.RestfulWebServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	//INTERNATIONALISING
	@Bean
	public LocaleResolver localeResolver(){


		//creating an instance of localeResolver
		/**SessionLocaleResolver localeResolver = new SessionLocaleResolver();*/

		//since we are accepting-language from the request header
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
/**
	//we can also configure this in application.properties file
	//to read the messages.properties file accordingly as per the location
	@Bean
	public ResourceBundleMessageSource messageSource(){
		//creating an instance of messageSource
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
*/
}
