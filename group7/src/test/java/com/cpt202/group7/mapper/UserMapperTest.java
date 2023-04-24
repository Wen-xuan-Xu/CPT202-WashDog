package com.cpt202.group7.mapper;

import com.cpt202.group7.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
class UserMapperTest {

    @Autowired UserMapper userMapper;
    @Test
    void findByUsername() {
        User user = userMapper.findByUsername("da@gmail.com");
        assertNotNull(user);
        assertEquals("da@gmail.com",user.getUsername());
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setGender("male");
        user.setNickname("Test User");
        user.setPhone("1234567890");
        user.setRole("CUSTOMER");

        userMapper.saveUser(user);
        User foundUser = userMapper.findByUsername("testuser");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("password", foundUser.getPassword());
        assertEquals("male", foundUser.getGender());
        assertEquals("Test User", foundUser.getNickname());
        assertEquals("1234567890", foundUser.getPhone());
        assertEquals("CUSTOMER", foundUser.getRole());
    }

    @Test
    void getCurrentUserID() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setGender("male");
        user.setNickname("Test User");
        user.setPhone("1234567890");
        user.setRole("CUSTOMER");
        userMapper.saveUser(user);

        int currentUserId = userMapper.getCurrentUserID("testuser");

        assertNotNull(currentUserId);
    }

    @Test
    void getCurrentUserPhoto() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setGender("male");
        user.setNickname("Test User");
        user.setPhone("1234567890");
        user.setRole("CUSTOMER");
        user.setPhoto("testphoto.jpg");
        userMapper.saveUser(user);

        String currentUserPhoto = userMapper.getCurrentUserPhoto("testuser");
        assertNotNull(userMapper.findByUsername("testuser"));
        assertNotNull(currentUserPhoto);
        assertEquals("testphoto.jpg", currentUserPhoto);
    }
}