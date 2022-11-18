package com.example.contact.repository;

import com.example.contact.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    boolean existsUserByUserName(String userName);

    void deleteUserByUserName(String userName);
    User findUserByUserName(String userName);
    User searchByPasswordAndUserName(String password , String userName);
















}
