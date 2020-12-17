package com.labcerebrone.RestfulWebServices.controller;

import com.labcerebrone.RestfulWebServices.Repository.UserJPARepository;
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
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    @Autowired
    private UserJPARepository userJPARepository;

    //Retrieve All Users Using HATEOAS
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){

        return userJPARepository.findAll();
    }

    //Retrieve a user based on ID with a Link to Retrieve All Users Using Hateoas
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        //what if the user is not created and still we get a status code of 200 OK for no user
        //Also in JPA We create Optional User cuz when th e user doesn't exist the optional
        //comes with a proper object
        Optional<User> user = userJPARepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id" + id);

        //Link to all users using Hateoas
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());//retrieveAllUser is taken from above fn.
        resource.add(linkTo.withRel("all-users"));

        //HATEOAS
        //returning back both resource and data
        return resource;
    }

    // We want to Create A new User For Doing Post Operations for /users URI,
    // return a status code of CREATED too, use PostMan for this Method
    @PostMapping("/jpa/users")
    //when we would @RequestBody on a Parameter then whatever passed in the body
    //of the request would be mapped(@PostMapping) to the user parameter
    //to validate the content of the new user use @Valid
    public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user){
        // the user id would be assigned by the UserDaoService
        User userSaveUser = userJPARepository.save(user);
        //Return A status of Creation along with the userid just got created
        //to map the URI to the location we use
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(userSaveUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    //Delete the Resource User By ID
    @DeleteMapping("/jpa/users/{id}")
    public void  DeleteUser(@PathVariable int id){
        //in JPA if the delete fails it would throw an exception
        userJPARepository.deleteById(id);
    }

}

