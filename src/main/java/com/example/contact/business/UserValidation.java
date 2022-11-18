package com.example.contact.business;

import com.example.contact.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidation {

    private final UserBusiness userBusiness;

    public UserValidation(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    public Boolean userExist(String userName) {

        if (userBusiness.userExist(userName)) {
            return true;
        } else {
            return false;
        }
    }

    public User checkPassword(String password, String userName) {
        User user = userBusiness.checkPassword(password, userName);
        return user;
    }


}
