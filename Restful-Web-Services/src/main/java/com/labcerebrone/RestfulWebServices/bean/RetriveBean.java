package com.labcerebrone.RestfulWebServices.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Another Way to Exclude Secured Variables is to use JsonIgnoreProperties,StaticFiltering
//@JsonIgnoreProperties(value = "field3") //for list use (value = {"field1","field2"})
@JsonFilter(value = "SomeBean") //for Dynamic Filtering to Work Enable this
public class RetriveBean {
    private String field1;
    private String field2;

    //If We Want Our Field3 To Be Secured(1st Approach),StaticFiltering
    //@JsonIgnore
    private String field3;

    public RetriveBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}

