package com.labcerebrone.RestfulWebServices.controller;


import com.labcerebrone.RestfulWebServices.version.Name;
import com.labcerebrone.RestfulWebServices.version.Person;
import com.labcerebrone.RestfulWebServices.version.Person2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {


    //URI Versioning for Version Control -> GitHub
    @GetMapping(path = "v1/person")
    public Person person(){
        return new Person("Bob Charlie");
    }

    @GetMapping(path = "v2/person")
    public Person2 person2(){
        return new Person2(new Name("Bob","Charlie"));
    }

    //Using Params For Version Control -> Amazon
    @GetMapping(value = "/person/param",params = "version=1")
    public Person paramV1(){return new Person("Bob Charlie");}

    @GetMapping(value = "/person/param",params = "version=2")
    public Person2 paramV2(){
        return new Person2(new Name("Bob","Charlie"));
    }

    //Using Header For Version Control -> Microsoft
    @GetMapping(value = "/person/header",headers = "X-API_VERSION=1")
    public Person headerV1(){return new Person("Bob Charlie");}

    @GetMapping(value = "/person/header",headers = "X-API_VERSION=2")
    public Person2 headerV2(){
        return new Person2(new Name("Bob","Charlie"));
    }


    //Using Accept Versioning For Version Control,Generally Used And Used By -> GitHub
    @GetMapping(value = "/person/produces",produces= "application/vnd.company.app-v1+json")
    public Person producerV1(){return new Person("Bob Charlie");}

    @GetMapping(value = "/person/produces",produces = "application/vnd.company.app-v2+json")
    public Person2 producerV2(){
        return new Person2(new Name("Bob","Charlie"));
    }
}



