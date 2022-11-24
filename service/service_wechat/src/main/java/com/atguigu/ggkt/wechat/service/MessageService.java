package com.atguigu.ggkt.wechat.service;

import java.util.Map;

/**
 * @author 王寒寒
 * @create 2022-11-23 10:40
 */
public interface MessageService {

    //接收消息
    String receiveMessage(Map<String, String> param);

    //推送模板订单支付成功消息
    void pushPayMessage(long l);
}
