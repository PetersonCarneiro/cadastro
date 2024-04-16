package com.cadastro.services;

import com.cadastro.domain.User;
import com.cadastro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public void saveUser(User user){
        this.repository.save(user);
    }
}
