package com.example.controller;

import com.example.dto.User;
import com.example.entity.UserEntity;
import com.example.entity.UserRole;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;
    /*@Autowired
    /*public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }*/
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping ("/userPage")
    public User getUser(@RequestBody User user){

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setUserRole(UserRole.valueOf(user.getUserRole()));

        userRepository.save(userEntity);

        user.setId(userEntity.getId());

        return  user;
    }

    @GetMapping ("/getUser")
    public User getUserId(@RequestParam(value="id")Integer id){

        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);

        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();

            User user= new User();
            user.setId(userEntity.getId());
            user.setName(userEntity.getName());
            user.setUserRole(String.valueOf(userEntity.getUserRole()));
            return  user;
        }
        return null;
    }

    @PutMapping("/updateUser")
    public User updateUser(@Nullable @RequestBody User user){

        Optional<UserEntity> optionalUserEntity = userRepository.findById(user.getId());

        if(optionalUserEntity.isPresent()){
            UserEntity userEntity = optionalUserEntity.get();

            userEntity.setName(user.getName());

            userRepository.save(userEntity);

            user.setName(userEntity.getName());

            return user;

        }

        return null;
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam(value="id")Integer id){

       if (userRepository.existsById(id)) {

           userRepository.deleteById(id);

       }


    }
}
