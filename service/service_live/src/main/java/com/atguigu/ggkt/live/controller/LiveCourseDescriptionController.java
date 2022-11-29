package com.atguigu.ggkt.live.controller;


import com.atguigu.ggkt.model.live.LiveCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程简介 前端控制器
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-30
 */
@RestController
@RequestMapping("/live/live-course-description")
public class LiveCourseDescriptionController {

    public interface LiveCourseDescriptionService extends IService<LiveCourseDescription> {
        LiveCourseDescription getByLiveCourseId(Long liveCourseId);
    }

}

