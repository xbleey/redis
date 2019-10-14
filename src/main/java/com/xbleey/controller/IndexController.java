/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MessageController
 * Author:   11580
 * Date:     2019/10/2 0002 15:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.compent.ColorUtils;
import com.xbleey.compent.RedisUtils;
import com.xbleey.entity.Message;
import com.xbleey.exception.MessNullPoint;
import com.xbleey.service.LoginService;
import com.xbleey.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/2 0002
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @Autowired
    MessageService messageService;
    @Autowired
    LoginService loginService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ColorUtils colorUtils;


    @RequestMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request, Model model) {
        /*验证登录*/
        loginService.authInfo(request, model);

        /*获取message数据*/
        List<Message> messages = messageService.getTopThreeMessages();

        /*存入页面引擎*/
        model.addAttribute("messages", messages);
        model.addAttribute("messNum", messageService.getMessNums());
        return "index";
    }


    @PostMapping(value = "/info")
    public String saveMess(HttpServletResponse httpServletResponse, String infoMess, String user) {
        if (infoMess == null || infoMess.equals("")) {
            throw new MessNullPoint();
        }
        Message message = new Message(user, infoMess, new Date(), colorUtils.getOneColor());
        messageService.saveMessage(message);
        return "redirect:/index";
    }

}
 

