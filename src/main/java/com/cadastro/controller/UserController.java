package com.cadastro.controller;

import com.cadastro.domain.User;
import com.cadastro.dtos.UserDto;
import com.cadastro.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity getAllProducts(){
        var allPoducts = repository.findAllByActiveTrue();
        return  ResponseEntity.ok(allPoducts);
    }

    @PostMapping("/{id}")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        User newUser = new User(userDto);
        System.out.println(userDto);
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody UserDto userDto){

        Optional<User> userOptional = repository.findById(userDto.id());

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setDocument(userDto.document());
            return ResponseEntity.ok(user);
        }else{
            throw new EntityNotFoundException();
        }
    }

    //Deletar através do Body, deve se passar o id através do body
    @DeleteMapping()
    public ResponseEntity deleteUser(@RequestBody UserDto userDto){
        User newUser = new User(userDto);
        repository.delete(newUser);
        return ResponseEntity.noContent().build();
    }
/*
    //Deletar através do @PathVariable, necessário passar o id na url
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserId(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

*/

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUserActiveFalse(@PathVariable Long id){
        Optional<User> userOptional = repository.findById(id);
        if(userOptional.isPresent()) {
            User newUser = userOptional.get();
            newUser.setActive(false);
            return ResponseEntity.noContent().build();
        }else{
            return  ResponseEntity.noContent().build();
        }

    }
}
