package com.atguigu.ggkt.live.service;

import com.atguigu.ggkt.model.live.LiveCourseConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 直播课程配置表 服务类
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-30
 */
public interface LiveCourseConfigService extends IService<LiveCourseConfig> {


    //查看配置信息
    LiveCourseConfig getByLiveCourseId(Long id);

}
