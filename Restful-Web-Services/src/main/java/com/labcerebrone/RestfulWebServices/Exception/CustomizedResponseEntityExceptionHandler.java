package com.labcerebrone.RestfulWebServices.Exception;

import com.labcerebrone.RestfulWebServices.controller.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//ResponseEntityExceptionHandler is an Abstract Class that we are extending over here
// it's the dafault extension Handler Provided By Spring
//Make this used by all the controllers across this particular project
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //to handle all the exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        //whenever an Exception Happens, we want to create a new instance
        // of our specific Bean
        ExceptionResponseStructure exceptionResponseStructure = new ExceptionResponseStructure(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity(exceptionResponseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //For All UserNotFoundException, this structure would be Used
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request) {

        //whenever an UserNotException Happens, we want to create a new instance
        // of our specific Bean
        ExceptionResponseStructure exceptionResponseStructure = new ExceptionResponseStructure(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity(exceptionResponseStructure, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders httpHeaders,HttpStatus httpStatus,
                                                                  WebRequest request){
        ExceptionResponseStructure exceptionResponseStructure = new ExceptionResponseStructure(
                new Date(),
                //to reduce the amount of information to the consumer
                "Validation Failed",
                //to let the consumer know what's wrong
                ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponseStructure, HttpStatus.BAD_REQUEST);
    }
}
