package com.curso.curso.controllers;

import com.curso.curso.dao.UserDao;
import com.curso.curso.models.User;
import com.curso.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RequestMapping(value = "/api")
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "/user/{id}")
    public User getUser(@PathVariable long id){
        User user=new User();
        user.setId(id);
        user.setName("Azael");
        user.setLastName("Morales");
        user.setEmail("azaelmorales029@gmail.com");
        user.setPhone("9513968682");
        user.setPassword("****");
        return user;
    }

    @RequestMapping(value = "/users")
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return new ArrayList<>();
        }
        return userDao.getUsers();
    }

    @RequestMapping(value = "/edit")
    public User edit(){
        User user=new User();
        user.setName("Azael");
        user.setLastName("Morales");
        user.setEmail("azaelmorales029@gmail.com");
        user.setPhone("9513968682");
        user.setPassword("****");
        return user;
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        userDao.delete(id);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public void register(@RequestBody User user){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash=argon2.hash(1,1024,1,user.getPassword());
        user.setPassword(hash);
        userDao.register(user);
    }


    @RequestMapping(value = "find")
    public User find(){
        User user=new User();
        user.setName("Azael");
        user.setLastName("Morales");
        user.setEmail("azaelmorales029@gmail.com");
        user.setPhone("9513968682");
        user.setPassword("****");
        return user;
    }

}
