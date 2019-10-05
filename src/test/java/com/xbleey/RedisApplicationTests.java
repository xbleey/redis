package com.xbleey;

import com.xbleey.compent.SHA1Utils;
import com.xbleey.dto.UserDao;
import com.xbleey.entity.User;
import com.xbleey.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {


    @Autowired
    UserDao userDao;


    @Test
    public void messageTest() {
        User user = new User();
        user.setName("apple");
        user.setPassword(SHA1Utils.encodePassword("apple"));
        userDao.save(user);
    }
}
