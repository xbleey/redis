package com.xbleey;

import com.xbleey.compent.SHA1Utils;
import com.xbleey.dto.MessageDao;
import com.xbleey.dto.UserDao;
import com.xbleey.entity.Message;
import com.xbleey.entity.User;
import com.xbleey.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {


    @Autowired
    UserDao userDao;

    @Autowired
    MessageDao messageDao;


    @Test
     public void messageTest() {

    }
}
