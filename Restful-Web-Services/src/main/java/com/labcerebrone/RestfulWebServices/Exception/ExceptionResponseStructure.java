package com.labcerebrone.RestfulWebServices.Exception;

import java.util.Date;
//simple ExceptionResponse Bean creation
public class ExceptionResponseStructure {

    //TimeStamp for when the Exception Happened
    //Message for the Exception
    //Details of the Exception
    private Date timestamp;
    private String message;
    private String details;

    //Generate a Constructor
    public ExceptionResponseStructure(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    //Generate Getters
    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }

}
