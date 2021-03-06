/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: LoginController
 * Author:   11580
 * Date:     2019/10/4 0004 21:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.sun.istack.NotNull;
import com.xbleey.compent.CookieUtils;
import com.xbleey.compent.RedisUtils;
import com.xbleey.compent.SHA1Utils;
import com.xbleey.entity.User;
import com.xbleey.service.LoginService;
import com.xbleey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/4 0004
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    @Autowired
    RedisUtils redisUtils;

    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(Model model, HttpServletResponse response, @NotNull String username, @NotNull String password) {
        User user = userService.getOne(username);
        if (user.getPassword().equals(SHA1Utils.encodePassword(password))) {

            /*存入本地的cookie*/
            CookieUtils cookieUtils = new CookieUtils("user_login", String.valueOf(user.getId()), "/", 60 * 60 * 24);
            response.addCookie(cookieUtils.getCookie());
            /*设置可过期的redis键值对保证登录时长*/
            redisUtils.set("user_" + user.getId(), username, 1800L);

            return "redirect:/index";
        }


        model.addAttribute("error", true);
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        String userId = loginService.isLogin(request);
        if (!"noLogin".equals(userId)) {
            redisUtils.del("user_" + userId);
        }
        model.addAttribute("logout", true);
        return "login";
    }


}
 
