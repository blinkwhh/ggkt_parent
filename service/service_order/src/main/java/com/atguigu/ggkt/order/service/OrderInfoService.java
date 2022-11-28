package com.atguigu.ggkt.order.service;


import com.atguigu.ggkt.model.order.OrderInfo;
import com.atguigu.ggkt.vo.order.OrderFormVo;
import com.atguigu.ggkt.vo.order.OrderInfoQueryVo;
import com.atguigu.ggkt.vo.order.OrderInfoVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 订单表 订单表 服务类
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-19
 */
public interface OrderInfoService extends IService<OrderInfo> {

    //订单列表
    Map<String, Object> findPageOrderInfo(Page<OrderInfo> pageParam, OrderInfoQueryVo orderInfoQueryVo);

    //生成点播课程订单
    Long submitOrder(OrderFormVo orderFormVo) throws Exception;

    //根据id获取订单信息
    OrderInfoVo getOrderInfoVoById(Long id);

    void updateOrderStatus(String out_trade_no);
}
