package com.atguigu.ggkt.vod.service;

import java.io.InputStream;
import java.util.Map;

/**
 * @author 王寒寒
 * @create 2022-11-17 22:58
 */
public interface VodService {
    //上传视频
    String uploadVideo(InputStream inputStream, String originalFilename);
    //删除视频
    void removeVideo(String videoSourceId);
    //获取点播视频参数
    Map<String,Object> getPlayAuth(Long courseId, Long videoId) throws Exception;
}
