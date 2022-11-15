package com.atguigu.ggkt.vod.mapper;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-10-20
 */
@Service
public interface CourseMapper extends BaseMapper<Course> {

    //根据id获取课程发布信息
    CoursePublishVo selectCoursePublishVoById(Long id);
}
