package com.tck.party.service.impl;

import com.tck.party.common.exception.FileException;
import com.tck.party.common.properties.AliyunOSSProperties;
import com.tck.party.common.utils.AliyunOssUtil;
import com.tck.party.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    AliyunOssUtil aliyunOssUtil;

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

    public String uploadFile(MultipartFile file) throws Exception {
        if (file == null) {
            return "";
        }
        try {
            return aliyunOssUtil.uploadFile(file, aliyunOSSProperties.getBucketApp(), null, aliyunOSSProperties.getDomainApp());
        } catch (Exception e) {
            logger.error("uploadFile error e:{}", e);
            throw new FileException("uploadFile error");
        }
    }
}
