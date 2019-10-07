/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: LoginService
 * Author:   11580
 * Date:     2019/10/5 0005 20:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.compent.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/5 0005
 * @since 1.0.0
 */
@Service
public class LoginService {

    @Autowired
    RedisUtils redisUtils;

    public String isLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String userId = null;

        /*获取cookie中的userId值*/
        if (cookies == null) {
            return "noLogin";
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user_login")) {
                    userId = cookie.getValue();
                }
            }
        }

        /*没有UserId直接返回未登录*/
        if (userId == null) {
            return "noLogin";
        }

        /*查询redis有无记录，有则已经登录*/
        String redisKey = "user_" + userId;
        return redisUtils.get(redisKey) == null ? "noLogin" : userId;
    }
}
 

