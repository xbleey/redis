/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MessageController
 * Author:   11580
 * Date:     2019/10/14 0014 9:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.controller;

import com.xbleey.compent.ColorUtils;
import com.xbleey.entity.Message;
import com.xbleey.exception.MessNullPoint;
import com.xbleey.service.LoginService;
import com.xbleey.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/14 0014
 * @since 1.0.0
 */
@Controller
public class MessageController {
    @Autowired
    LoginService loginService;
    @Autowired
    MessageService messageService;
    @Autowired
    ColorUtils colorUtils;


    @GetMapping(value = "/message")
    public String getAllMessage(HttpServletRequest request, Model model) {
        /*验证登录*/
        loginService.authInfo(request, model);

        /*获取message数据*/
        List<Message> messages = messageService.getAllMessages();

        /*存入页面引擎*/
        model.addAttribute("messages", messages);
        model.addAttribute("messNum", messageService.getMessNums());
        return "message/message";
    }

    @PostMapping(value = "/info")
    public String saveMess(HttpServletResponse response, String infoMess, String user) {
        if (infoMess == null || infoMess.equals("")) {
            throw new MessNullPoint();
        }
        Message message = new Message(user, infoMess, new Date(), colorUtils.getOneColor());
        messageService.saveMessage(message);
        response.setStatus(201);
        return "redirect:/index";
    }
}
 

