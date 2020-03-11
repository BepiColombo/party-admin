package com.tck.party.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aliyun-oss")
public class AliyunOSSProperties {

    /**
     * 服务器地点
     */
    private String region;
    /**
     * 服务器地址
     */
    private String endpoint;
    /**
     * OSS身份id
     */
    private String accessKeyId;
    /**
     * 身份密钥
     */
    private String accessKeySecret;

    /**
     * App文件bucketName
     */
    private String bucketApp;
    /**
     * App包文件地址前缀
     */
    private String domainApp;
}
