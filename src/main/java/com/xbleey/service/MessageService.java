/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: MessageService
 * Author:   11580
 * Date:     2019/10/2 0002 15:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xbleey.service;

import com.xbleey.dto.MessageDao;
import com.xbleey.entity.DayMenu;
import com.xbleey.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 11580
 * @create 2019/10/2 0002
 * @since 1.0.0
 */
@Service
public class MessageService {

    @Autowired
    MessageDao messageDao;

    public List<Message> getTopTenMessages() {
        return messageDao.findTen();
    }

    public List<Message> getAllMessages() {
        return messageDao.findAll();
    }

    public Integer getMessNums() {
        return messageDao.getNums();
    }

    public void saveMessage(Message message) {
        if (message.getNum() != 0) {
            messageDao.save(message);
        }
    }

    public DayMenu getMenuByDays(int beforeDay) {
        DayMenu dayMenu = new DayMenu(beforeDay);
        dayMenu.setMenus(messageDao.getListToday(dayMenu.getDate() + "%"));
        return dayMenu;
    }

    public void delete(int messageId) {
        messageDao.deleteById(messageId);
    }
}
 

