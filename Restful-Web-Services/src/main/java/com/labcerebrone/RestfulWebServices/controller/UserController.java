package com.labcerebrone.RestfulWebServices.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.labcerebrone.RestfulWebServices.bean.User;
import com.labcerebrone.RestfulWebServices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
/** Simple RestFul Service **/

    //IMPORT the UserDaoService to return the users
    @Autowired//autowiring is done as the to get the Bean Instance Autowired to this
    private UserDaoService userDaoService;


    //Retrieve All Users Using HATEOAS
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return userDaoService.findAll();
    }

    //Retrieve a user based on ID with a Link to Retrieve All Users Using Hateoas
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        //what if the user is not created and still we get a status code of 200 OK for no user
        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id" + id);

        //Link to all users using Hateoas
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());//retrieveAllUser is taken from above fn.
        resource.add(linkTo.withRel("all-users"));

        //HATEOAS
        //returning back both resource and data
        return resource;
    }

    // We want to Create A new User For Doing Post Operations for /users URI,
    // return a status code of CREATED too, use PostMan for this Method
    @PostMapping("/users")
    //when we would @RequestBody on a Parameter then whatever passed in the body
    //of the request would be mapped(@PostMapping) to the user parameter
    //to validate the content of the new user use @Valid
    public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user){
        // the user id would be assigned by the UserDaoService
        User userSaveUser = userDaoService.save(user);
        //Return A status of Creation along with the userid just got created
        //to map the URI to the location we use
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().
                path("/{id}").
                buildAndExpand(userSaveUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    //Delete the Resource User By ID
    @DeleteMapping("/users/{id}")
    public void  DeleteUser(@PathVariable int id){
        //what if the user is not created and still we get a status code of 200 OK for no user
        User user = userDaoService.DeleteOne(id);
        if (user == null)
            throw new UserNotFoundException("id" + id);
    }


}
