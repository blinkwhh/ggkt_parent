package com.atguigu.ggkt.order.service;

import java.util.Map;

/**
 * @author 王寒寒
 * @create 2022-11-28 17:17
 */
public interface WXPayService {

    Map createJsapi(String orderNo);

}
