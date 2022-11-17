package com.atguigu.ggkt.vod.service;

import java.io.InputStream;

/**
 * @author 王寒寒
 * @create 2022-11-17 22:58
 */
public interface VodService {
    //上传视频
    String uploadVideo(InputStream inputStream, String originalFilename);
    //删除视频
    void removeVideo(String videoSourceId);
}
