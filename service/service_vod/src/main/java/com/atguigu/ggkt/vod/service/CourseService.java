package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-20
 */
public interface CourseService extends IService<Course> {

    //点播课程列表
    Map<String, Object> findPage(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    //添加课程基本信息
    Long saveCourseInfo(CourseFormVo courseFormVo);
}
