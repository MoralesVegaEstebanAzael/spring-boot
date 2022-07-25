package com.curso.curso.dao;

import com.curso.curso.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUser(long id);

    void delete(long id);

    void register(User user);

    User getUserByCredentials(User user);
}
