package com.atguigu.ggkt.vod.mapper;

import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-10-18
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {

}
