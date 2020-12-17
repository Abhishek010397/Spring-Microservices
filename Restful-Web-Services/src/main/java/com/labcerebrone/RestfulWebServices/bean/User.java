package com.labcerebrone.RestfulWebServices.bean;

import com.labcerebrone.RestfulWebServices.Entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "All Details about the User")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    //to validate name having a size of minimum 2
    @Size(min = 2,message = "Name should have atleast 2 characters")
    @ApiModelProperty(notes = "Name should have atleast 2 characters")
    private String name;

    //birthdate should always be in past not in future,so validate it too!!
    @Past
    @ApiModelProperty(notes = "BirthDate should be in the past")
    private Date BirthDate;

    //Relationship b/w Post and User,also ensure the mapping Attribute
    @OneToMany(mappedBy = "user" )
    private List<Post> posts;

    //Getters And Setters for post
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        BirthDate = birthDate;
    }

    public User(){}

    @Override
    public String toString() {
        return String.format("User [id=%s,birthDate=%s]",id,name,BirthDate);

    }
}
