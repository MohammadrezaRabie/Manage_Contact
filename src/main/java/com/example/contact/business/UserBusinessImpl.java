package com.example.contact.business;

import com.example.contact.entity.User;
import com.example.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserBusinessImpl implements UserBusiness {

    private final UserRepository userRepository;


    public UserBusinessImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public Boolean userExist(String userName) {
        return userRepository.existsUserByUserName(userName);
    }

    @Override
    public User checkPassword(String password, String userName) {
        User user = userRepository.searchByPasswordAndUserName(password, userName);
        return user;
    }

}
