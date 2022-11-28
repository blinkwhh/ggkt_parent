package com.atguigu.ggkt.client.course;

import com.atguigu.ggkt.model.vod.Course;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 王寒寒
 * @create 2022-11-23 10:34
 */
@Api(description = "远程调用接口，会被wechat模块调用")
@FeignClient(value = "service-vod")
public interface CourseFeignClient {
    @ApiOperation("根据关键字查询课程")
    @GetMapping("/api/vod/course/inner/findByKeyword/{keyword}")
    List<Course> findByKeyword(@PathVariable String keyword);

    @ApiOperation("根据ID查询课程")
    @GetMapping("/api/vod/course/inner/getById/{courseId}")
    Course getById(@PathVariable Long courseId);
}
