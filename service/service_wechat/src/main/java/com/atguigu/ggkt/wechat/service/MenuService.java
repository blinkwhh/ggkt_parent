package com.atguigu.ggkt.wechat.service;

import com.atguigu.ggkt.model.wechat.Menu;
import com.atguigu.ggkt.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-22
 */
public interface MenuService extends IService<Menu> {

    //获取全部菜单
    List<MenuVo> findMenuInfo();

    //获取一级菜单
    List<Menu> findMenuOneInfo();
}
