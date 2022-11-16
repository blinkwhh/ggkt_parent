package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.enums.ResultCodeEnum;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-09-25
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value="/admin/vod/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

//    /**
//     * 1、查询所有讲师
//     * http://localhost:8301/admin/vod/teacher/findAll
//     * 为统一返回JSON类型数据，这个方法在下边重新编写
//     */
//    @ApiOperation("查询所有讲师")
//    @GetMapping("findAll")
//    public List<Teacher> findALLTeacher() {
//        List<Teacher> list = teacherService.list();
//        return list;
//    }
//
//    /**
//     * 删除讲师，deletemapping不能直接在网页测试
//     * @param id
//     * @return
//     * 为统一返回JSON类型数据，这个方法在下边重新编写
//     */
//    @ApiOperation("逻辑删除讲师")
//    @DeleteMapping("remove/{id}")
//    public boolean removeById(@ApiParam(name = "id", value = "ID", required = true)
//                                  @PathVariable String id) {
//        return teacherService.removeById(id);
//    }


    // http://localhost:8301/admin/vod/teacher/findAll
    //1 查询所有讲师
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        //调用service方法
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message(ResultCodeEnum.SUCCESS.getMessage()).code(20000);
    }

    // remove/1
    //2 逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if(isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //3 条件查询分页列表
    @ApiOperation(value = "获取分页列表")
    @PostMapping("findQueryPage/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherVo", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(page, limit);
        //获取条件值
        String name = teacherQueryVo.getName();//讲师名称
        Integer level = teacherQueryVo.getLevel();//讲师级别
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
        //封装条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("join_date",joinDateBegin);
        }
        if(!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("join_date",joinDateEnd);
        }
        //调用方法得到分页查询结果
        IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "新增")
    @PostMapping("saveTeacher")
    public Result save(@RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.ok(null);
    }

    @ApiOperation(value = "获取")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        teacherService.removeByIds(idList);
        return Result.ok(null);
    }
}

