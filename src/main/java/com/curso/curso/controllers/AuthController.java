package com.curso.curso.controllers;

import com.curso.curso.dao.UserDao;
import com.curso.curso.models.User;
import com.curso.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User userAuth =  userDao.getUserByCredentials(user);
        if(userAuth!=null){
            String tokenJwt = jwtUtil.create(String.valueOf(userAuth.getId()),userAuth.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }
}
