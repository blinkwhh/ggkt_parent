package com.atguigu.ggkt.live.controller;


import com.atguigu.ggkt.live.service.LiveCourseAccountService;
import com.atguigu.ggkt.live.service.LiveCourseService;
import com.atguigu.ggkt.model.live.LiveCourse;
import com.atguigu.ggkt.model.live.LiveCourseAccount;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.live.LiveCourseFormVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 直播课程表 前端控制器
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-30
 */
@RestController
@RequestMapping(value = "/admin/live/liveCourse")
public class LiveCourseController {

    @Autowired
    private LiveCourseService liveCourseService;

    @Autowired
    private LiveCourseAccountService liveCourseAccountService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<LiveCourse> pageParam = new Page<>(page, limit);
        IPage<LiveCourse> pageModel = liveCourseService.selectPage(pageParam);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody LiveCourseFormVo liveCourseVo) throws Exception {
        liveCourseService.save(liveCourseVo);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        liveCourseService.removeLive(id);
        return Result.ok(null);
    }

    //id查询课程基本信息
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<LiveCourse> get(@PathVariable Long id) {
        LiveCourse liveCourse = liveCourseService.getById(id);
        return Result.ok(liveCourse);
    }

    //id查询直播课程基本信息和描述信息
    @ApiOperation(value = "获取")
    @GetMapping("getInfo/{id}")
    public Result<LiveCourseFormVo> getInfo(@PathVariable Long id) {
        return Result.ok(liveCourseService.getLiveCourseFormVo(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody LiveCourseFormVo liveCourseVo) {
        liveCourseService.updateLiveById(liveCourseVo);
        return Result.ok(null);
    }

    @ApiOperation(value = "获取")
    @GetMapping("getLiveCourseAccount/{id}")
    public Result<LiveCourseAccount> getLiveCourseAccount(@PathVariable Long id) {
        return Result.ok(liveCourseAccountService.getByLiveCourseId(id));
    }

    @ApiOperation(value = "获取")
    @GetMapping("getCourseConfig/{id}")
    public Result getCourseConfig(@PathVariable Long id) {
        return Result.ok(liveCourseService.getCourseConfig(id));
    }
}

