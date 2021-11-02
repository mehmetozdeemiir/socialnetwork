package com.backend.socialnetwork.repositories;

import com.backend.socialnetwork.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserNameEquals(String userName);

    User findByUserName(String userName);

    //boolean existsUserByPhoneNumber(String phoneNumber);
}

