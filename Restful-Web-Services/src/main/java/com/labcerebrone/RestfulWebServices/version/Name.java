package com.labcerebrone.RestfulWebServices.version;

public class Name {

    private String firstName;
    private  String lastName;

    //No-Argumented Constructor
    public Name(){}


    //Argumented Constructor
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
