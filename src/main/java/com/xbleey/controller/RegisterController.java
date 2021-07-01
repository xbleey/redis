/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: RegisterController
 * Author:   11580
 * Date:     2019/11/4 0004 17:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.sun.istack.NotNull;
import com.xbleey.compent.SHA1Utils;
import com.xbleey.entity.User;
import com.xbleey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/11/4 0004
 * @since 1.0.0
 */
@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String Register(Model model, @NotNull String userName, @NotNull String passWord, @NotNull String checkPassWord) {
        if (!passWord.equals(checkPassWord)) {
            model.addAttribute("error", true);
            return "register";
        }
        if (userService.existUsername(userName)) {
            model.addAttribute("same", true);
            return "register";
        }
        User user = new User();
        user.setName(userName);
        user.setPassword(SHA1Utils.encodePassword(passWord));
        userService.SaveUser(user);
        return "redirect:/login";
    }
}
 

