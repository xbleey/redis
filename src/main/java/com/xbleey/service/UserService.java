/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserService
 * Author:   11580
 * Date:     2019/9/27 0027 16:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dto.UserDao;
import com.xbleey.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/9/27 0027
 * @since 1.0.0
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getAll() {
        return userDao.findAll();
    }

    public User SaveUser(User user) {
        return userDao.save(user);
    }

    public User getOne(int id) {
        return userDao.getFirstByid(id);
    }

    public int deleteUser(int id) {
        userDao.deleteById(id);
        return id;
    }
}
 

