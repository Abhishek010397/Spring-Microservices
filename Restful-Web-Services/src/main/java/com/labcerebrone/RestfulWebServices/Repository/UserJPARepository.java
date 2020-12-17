package com.labcerebrone.RestfulWebServices.Repository;


import com.labcerebrone.RestfulWebServices.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User,Integer> {
}
