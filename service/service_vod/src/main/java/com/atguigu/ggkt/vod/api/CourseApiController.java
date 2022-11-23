package com.atguigu.ggkt.vod.api;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王寒寒
 * @create 2022-11-23 10:20
 */
@Api(description = "该方法会在wechat模块中被调用", tags = "课程查询接口")
@RestController
@RequestMapping("/api/vod/course")
public class CourseApiController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("根据关键字查询课程")
    @GetMapping("inner/findByKeyword/{keyword}")
    public List<Course> findByKeyword(
            @ApiParam(value = "关键字", required = true)
            @PathVariable String keyword){
        QueryWrapper<Course> queryWrapper = new QueryWrapper();
        queryWrapper.like("title", keyword);
        List<Course> list = courseService.list(queryWrapper);
        return list;
    }

}
