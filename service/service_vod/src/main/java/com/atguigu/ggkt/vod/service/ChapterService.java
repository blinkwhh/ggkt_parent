package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-20
 */
public interface ChapterService extends IService<Chapter> {

    //章节小结列表
    List<ChapterVo> getNestedTreeList(Long courseId);

    //根据课程id删除章节
    void removeChapterByCourseId(Long id);
}
