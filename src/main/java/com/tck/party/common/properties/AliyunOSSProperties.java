package com.tck.party.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aliyun-oss")
public class AliyunOSSProperties {


    private String region;

    //服务器地址
    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketApp;

    private String domainApp;
}
