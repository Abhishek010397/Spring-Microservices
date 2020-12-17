package com.labcerebrone.RestfulWebServices.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.labcerebrone.RestfulWebServices.bean.RetriveBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    @GetMapping("/filtering")
    public RetriveBean retriveBean(){
        return new RetriveBean("value1","value2","value3");
    }

    //to return a List of Bean
    @GetMapping("/filtering-list")
    public List<RetriveBean> retriveListBean(){
        return Arrays.asList(new RetriveBean("value1","value2","value3"),
                            new RetriveBean("value12","value22","value32"));
    }

    //Using Dynamic Filtering
    @GetMapping("/filter")
    public MappingJacksonValue retriveBean1(){
        RetriveBean retriveBean1 = new RetriveBean("value11","value22","value33");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(retriveBean1);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }


    @GetMapping("/filter-list")
    public MappingJacksonValue retriveBeanList(){
        List<RetriveBean> list = Arrays.asList(new RetriveBean("1","2","3"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBean",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;

    }
}
