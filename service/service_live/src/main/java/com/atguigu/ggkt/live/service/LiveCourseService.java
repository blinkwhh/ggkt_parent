package com.atguigu.ggkt.live.service;


import com.atguigu.ggkt.model.live.LiveCourse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 直播课程表 服务类
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-30
 */
public interface LiveCourseService extends IService<LiveCourse> {

    //直播课程分页查询
    IPage<LiveCourse> selectPage(Page<LiveCourse> pageParam);
}
