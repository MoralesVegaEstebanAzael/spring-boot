package com.curso.curso.dao;

import com.curso.curso.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<User> getUsers() {
        String query = "FROM User";
        return  entityManager.createQuery(query).getResultList();
    }

    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public void delete(long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByCredentials(User user) {
        String query = "FROM User WHERE email=:email";
        List<User> result = entityManager.createQuery(query)
                .setParameter("email",user.getEmail())
                .getResultList();
        if(result.isEmpty())return null;

        String password=result.get(0).getPassword();
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        System.out.println("argon 2:"+argon2.verify(password,user.getPassword()));
//        if(argon2.verify(password,user.getPassword())){
//            return result.get(0);
//        }
//        return null;
        return argon2.verify(password,user.getPassword())?result.get(0):null;
    }

}
