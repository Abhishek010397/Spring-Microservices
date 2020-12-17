package com.labcerebrone.RestfulWebServices.controller;



import com.labcerebrone.RestfulWebServices.bean.HelloWorldBean;
import com.labcerebrone.RestfulWebServices.bean.User;
import org.dom4j.QName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController //to make spring know that this is a controller that too Rest One
public class HelloWorldController {

    //Update this to use Message Source
    @Autowired
    private MessageSource messageSource;


    //GET Method to map GET Request to the URI for /hello-world
    //we can use @GetMapping -> @GetMapping(path = "/hello-world")
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    // Method to return Hello-World after making  a GET request to the specified URI
    public String helloWorld () {
        return "Hello World";
    }
    //create a bean and return it on making a GET Request
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello-World");
        //returns a bean
    }
    //create a path variable,%s would be replaced with name
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable (@PathVariable String name){
        return new HelloWorldBean(String.format("Hello-World, %s", name));
        //output would be "message": "Hello-World, aa" , uri: http://localhost:8080/hello-world/path-variable/aa
    }
/**
    //Based on the accept Language Header we would decide the locale
    @GetMapping(path = "hello-world/internationalised")
    public String helloWorldInternationalised(@RequestHeader(name = "Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
    }
*/

    //Another Way to get Locale is using LocaleContextHolder, so that we dont need to
    //configure locale as a request parameter thereby we have made
    // use of AcceptHeaderLocaleResolver
    @GetMapping(path = "hello-world/internationalised")
    public String helloWorldInternationalised(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
