package com.example.contact.business;

import com.example.contact.entity.User;
import org.springframework.stereotype.Service;


public interface UserBusiness {

    User saveUser(User user);

    User findUserByUserName(String userName);

    void deleteUser(User user);

    Boolean userExist(String userName);

    User checkPassword(String password, String userName);

}
