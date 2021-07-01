package com.xbleey.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbleey.entity.DayMenu;
import com.xbleey.entity.Message;
import com.xbleey.entity.SentBody;
import com.xbleey.entity.Text;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RobotService {

    private static final Logger logger = LoggerFactory.getLogger(RobotService.class);
    ObjectMapper obj;
    @Autowired
    MessageService messageService;


    @Scheduled(cron = "0 35 10 * * ? ")
    public void riceMsg() {
        try {
            obj = new ObjectMapper();
            SentBody sentBody = new SentBody("text", new Text(build(), new String[]{"陈诗华"}));
            String json = obj.writeValueAsString(sentBody);
            sent(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 10 * * ? ")
    public void callRiceMsg() {
        try {
            obj = new ObjectMapper();
            SentBody sentBody = new SentBody("text", new Text("快来选午晚饭啊！link：http://8.129.102.0", new String[]{"@all"}));
            String json = obj.writeValueAsString(sentBody);
            sent(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String build() {
        DayMenu today = messageService.getMenuByDays(0);

        StringBuilder sendMsg = new StringBuilder();
        sendMsg.append("今天是" + today.getDate() + " " + today.getWeek() + "\n");
        sendMsg.append("午餐：\n");
        for (Message message : today.getMenus()) {
            sendMsg.append(message.getWords() + "（" + message.getNum() + "份）\n");
        }
        sendMsg.append("合计：" + today.getRiceNum() + "份\n");

        sendMsg.append("\n晚餐：\n");
        for (Message message : today.getMenus()) {
            sendMsg.append(message.getWords() + "（" + message.getNum() + "份）\n");
        }
        sendMsg.append("合计：" + today.getRiceNum() + "份\n");
        return sendMsg.toString();
    }

    private void sent(String msg) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            System.out.println("----------------------------------------");
            logger.info(msg);

            HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=70409f47-6b86-4e66-ae8c-dbc475edf897");
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(msg, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPost.setEntity(stringEntity);


            System.out.println("Executing request " + httpPost.getRequestLine());

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {//
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException(
                                "Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            logger.info(responseBody);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

