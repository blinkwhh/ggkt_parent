package com.atguigu.ggkt.live.api;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.ggkt.live.service.LiveCourseService;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.utils.AuthContextHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王寒寒
 * @create 2022-11-30 22:29
 */
@RestController
@RequestMapping("api/live/liveCourse")
public class LiveCourseApiController {

    @Resource
    private LiveCourseService liveCourseService;

    @ApiOperation(value = "获取用户access_token")
    @GetMapping("getPlayAuth/{id}")
    public Result<JSONObject> getPlayAuth(@PathVariable Long id) {
        JSONObject object = liveCourseService.getPlayAuth(id, AuthContextHolder.getUserId());
        return Result.ok(object);
    }


}
