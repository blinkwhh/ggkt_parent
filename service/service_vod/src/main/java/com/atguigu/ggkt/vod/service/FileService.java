package com.atguigu.ggkt.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 王寒寒
 * @create 2022-10-18 10:53
 */
@Service
public interface FileService {

    //文件上传
    String upload(MultipartFile file);
}
