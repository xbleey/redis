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
import com.xbleey.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(cacheNames = "message", key = "'all'")
    public List<Message> getAllMessages() {
        return messageDao.findAll();
    }

    @Cacheable(cacheNames = "messNums", key = "'num'", sync = true)
    public Integer getMessNums() {
        return messageDao.getNums();
    }

    @CacheEvict(value = "message", key = "'all'")
    public void saveMessage(Message message) {
        if (message.getUser() == null || message.getUser().equals("")) {
            message.setUser("游客");
        }
        messageDao.save(message);
    }
}
 

