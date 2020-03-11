package com.tck.party.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface FileService {

    String uploadFile(MultipartFile files) throws Exception;
}
