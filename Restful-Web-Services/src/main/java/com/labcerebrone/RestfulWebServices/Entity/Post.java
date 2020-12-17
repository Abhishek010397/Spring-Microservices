package com.labcerebrone.RestfulWebServices.Entity;

import com.labcerebrone.RestfulWebServices.bean.User;

//Many to One Relationship between User and Post i.e User can Post N number of stuffs
import javax.persistence.*;


@Entity
public class Post {

    //Primary Key
    @Id
    @GeneratedValue
    private Integer id;
    private String description;


    //Annotation in JPA, to avoid recurssion b/w User And Post
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
