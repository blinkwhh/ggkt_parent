package com.atguigu.ggkt.live.service;


import com.alibaba.fastjson.JSONObject;
import com.atguigu.ggkt.model.live.LiveCourse;
import com.atguigu.ggkt.vo.live.LiveCourseConfigVo;
import com.atguigu.ggkt.vo.live.LiveCourseFormVo;
import com.atguigu.ggkt.vo.live.LiveCourseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    //新增直播课程
    Boolean save(LiveCourseFormVo liveCourseVo) throws Exception;

    //直播课程删除接口
    void removeLive(Long id);

    //修改
    void updateLiveById(LiveCourseFormVo liveCourseVo);

    //获取
    LiveCourseFormVo getLiveCourseFormVo(Long id);

    LiveCourseConfigVo getCourseConfig(Long id);

    //修改配置
    void updateConfig(LiveCourseConfigVo liveCourseConfigVo);

    //获取最近的直播
    List<LiveCourseVo> findLatelyList();

    //从欢拓云获取access_token
    JSONObject getPlayAuth(Long id, Long userId);

    //根据ID查询课程
    Map<String, Object> getInfoById(Long courseId);
}
