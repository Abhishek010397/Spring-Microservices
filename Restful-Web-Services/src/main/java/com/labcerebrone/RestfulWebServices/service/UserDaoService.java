package com.labcerebrone.RestfulWebServices.service;


import com.labcerebrone.RestfulWebServices.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* for User Component We create a User Controller **/

@Component//Spring Created A bean Of this
public class UserDaoService {

    //static ArrayList
    private static List<User> users = new ArrayList<>();

    //static list of 3 users
    static {
        users.add(new User(1,"Abhi",new Date()));
        users.add(new User(2,"Ram",new Date()));
        users.add(new User(3,"Raj",new Date()));
    }
    //the userid is typically set by the DB as it;s PrimaryKey
    private static int usersCount = 3;
    //creating few methods:-

    //1.public List<User> findAll()  to return a list of users
    public List<User> findAll(){
        return users;
    }

    //2.public User save(User user) to save the user to DB
    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount); //to make the new user with an ID of 4;
        }
        users.add(user);
        return user;
    }
    //public User findOne(int id) to find a specific User
    public User findOne(int id){
        for(User user:users){
            if(user.getId()==id) {
                return user;
            }
        }
        return null;
    }

    //Deleting the User by ID and returning Back the deleted Resource
    public User DeleteOne(int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId()==id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
