package com.atguigu.ggkt.live.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.ggkt.client.course.CourseFeignClient;
import com.atguigu.ggkt.live.mapper.LiveCourseMapper;
import com.atguigu.ggkt.live.mtcloud.CommonResult;
import com.atguigu.ggkt.live.mtcloud.MTCloud;
import com.atguigu.ggkt.live.service.LiveCourseAccountService;
import com.atguigu.ggkt.live.service.LiveCourseDescriptionService;
import com.atguigu.ggkt.live.service.LiveCourseService;
import com.atguigu.ggkt.model.live.LiveCourse;
import com.atguigu.ggkt.model.live.LiveCourseAccount;
import com.atguigu.ggkt.model.live.LiveCourseDescription;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.live.LiveCourseFormVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 直播课程表 服务实现类
 * </p>
 *
 * @author wanghanhan
 * @since 2022-11-30
 */
@Service
public class LiveCourseServiceImpl extends ServiceImpl<LiveCourseMapper, LiveCourse> implements LiveCourseService {

    @Autowired
    private CourseFeignClient courseFeignClient;

    @Autowired
    private CourseFeignClient teacherFeignClient;

    @Resource
    private LiveCourseDescriptionService liveCourseDescriptionService;

    @Resource
    private LiveCourseAccountService liveCourseAccountService;

    @Resource
    private MTCloud mtCloudClient;
    //直播课程分页查询
    @Override
    public IPage<LiveCourse> selectPage(Page<LiveCourse> pageParam) {
        IPage<LiveCourse> page = baseMapper.selectPage(pageParam, null);
        List<LiveCourse> liveCourseList = page.getRecords();

        for(LiveCourse liveCourse : liveCourseList) {
            Teacher teacher = courseFeignClient.getTeacherLive(liveCourse.getTeacherId());
            //这里判断查到的是否为空
            liveCourse.getParam().put("teacherName", teacher.getName());
            liveCourse.getParam().put("teacherLevel", teacher.getLevel());
        }
        return page;
    }

    @SneakyThrows
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean save(LiveCourseFormVo liveCourseVo) throws Exception {
        LiveCourse liveCourse = new LiveCourse();
        BeanUtils.copyProperties(liveCourseVo, liveCourse);

        Teacher teacher = teacherFeignClient.getTeacherLive(liveCourseVo.getTeacherId());
        HashMap<Object, Object> options = new HashMap<>();
        options.put("scenes", 2);//直播类型。1: 教育直播，2: 生活直播。默认 1，说明：根据平台开通的直播类型填写
        options.put("password", liveCourseVo.getPassword());
        String res = mtCloudClient.courseAdd(liveCourse.getCourseName(),
                teacher.getId().toString(),
                new DateTime(liveCourse.getStartTime()).toString("yyyy-MM-dd HH:mm:ss"),
                new DateTime(liveCourse.getEndTime()).toString("yyyy-MM-dd HH:mm:ss"),
                teacher.getName(), teacher.getIntro(), options);

        System.out.println("return:: "+res);
        CommonResult<JSONObject> commonResult = JSON.parseObject(res, CommonResult.class);
        if(Integer.parseInt(commonResult.getCode()) == MTCloud.CODE_SUCCESS) {
            JSONObject object = commonResult.getData();
            liveCourse.setCourseId(object.getLong("course_id"));
            baseMapper.insert(liveCourse);

            //保存课程详情信息
            LiveCourseDescription liveCourseDescription = new LiveCourseDescription();
            liveCourseDescription.setDescription(liveCourseVo.getDescription());
            liveCourseDescription.setLiveCourseId(liveCourse.getId());
            liveCourseDescriptionService.save(liveCourseDescription);

            //保存课程账号信息
            LiveCourseAccount liveCourseAccount = new LiveCourseAccount();
            liveCourseAccount.setLiveCourseId(liveCourse.getId());
            liveCourseAccount.setZhuboAccount(object.getString("bid"));
            liveCourseAccount.setZhuboPassword(liveCourseVo.getPassword());
            liveCourseAccount.setAdminKey(object.getString("admin_key"));
            liveCourseAccount.setUserKey(object.getString("user_key"));
            liveCourseAccount.setZhuboKey(object.getString("zhubo_key"));
            liveCourseAccountService.save(liveCourseAccount);
        } else {
            String getmsg = commonResult.getmsg();
            throw new Exception(getmsg);
        }
        return true;
    }
}
