package com.atguigu.ggkt.live.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 王寒寒
 * @create 2022-11-30 0:25
 */
@Data
@Component
@ConfigurationProperties(prefix = "mtcloud")
public class MTCloudAccountConfig {

    private String openId;
    private String openToken;

}
