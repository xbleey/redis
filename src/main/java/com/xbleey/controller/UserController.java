/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: UserDao
 * Author:   11580
 * Date:     2019/9/27 0027 16:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.entity.User;
import com.xbleey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/9/27 0027
 * @since 1.0.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping(value = "/")
    public User SaveUser(User user) {
        return userService.SaveUser(user);
    }

    @Cacheable(cacheNames = "user", key = "#id")
    @RequestMapping(value = "/{id}")
    public User getOne(@PathVariable(value = "id") Integer id) {
        return userService.getOne(id);
    }

    @CachePut(value = "user", key = "#user.id", condition = "#user.id != null ")
    @PutMapping(value = "/")
    public User updateUser(HttpServletResponse httpServletResponse, User user) {
        if (user.getId() == null) {
            try {
                httpServletResponse.sendError(403, "禁止使用该方法");
                return user;
            } catch (IOException o) {
                o.printStackTrace();
                return null;
            }
        }
        if (userService.getOne(user.getId()) == null) {
            try {
                httpServletResponse.sendError(404, "您要查找的资源不存在");
                return user;
            } catch (IOException o) {
                o.printStackTrace();
                return null;
            }
        }
        return userService.SaveUser(user);
    }

    @CacheEvict(value="user",key="#id")
    @DeleteMapping(value = "/{id}")
    public Integer deleteUser(@PathVariable(value = "id") Integer id) {
        return userService.deleteUser(id);
    }
}
 

