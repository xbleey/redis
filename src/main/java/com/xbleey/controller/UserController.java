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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/{id}")
    public User getOne(@PathVariable(value = "id") Integer id) {
        return userService.getOne(id);
    }

    @PutMapping(value = "/")
    public User updateUser(HttpServletResponse httpServletResponse, User user) {
        if (user.getId() == null) {
            httpServletResponse.setStatus(403);
            return null;
        }
        if (userService.getOne(user.getId()) == null) {
            httpServletResponse.setStatus(404);
            return null;
        }
        return userService.SaveUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public Integer deleteUser(@PathVariable(value = "id") Integer id) {
        return userService.deleteUser(id);
    }
}
 

