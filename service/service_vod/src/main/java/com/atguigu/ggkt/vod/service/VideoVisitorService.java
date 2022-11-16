package com.atguigu.ggkt.vod.service;


import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-16
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    //课程统计接口
    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}
