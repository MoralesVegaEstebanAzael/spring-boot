package com.curso.curso;

import com.curso.curso.dao.UserDao;
import com.curso.curso.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CursoApplicationTests {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserDao userDao;
    @Test
    void contextLoads() {
        User user = new User();
        user.setName("test");
        user.setLastName("test");
        user.setEmail("test@test.com");
        user.setPhone("3333");
        user.setPassword(encoder.encode("123"));
        userDao.register(user);
    }

}
