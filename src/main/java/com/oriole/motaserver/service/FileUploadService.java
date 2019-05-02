package com.oriole.motaserver.service;

import com.oriole.motaserver.dao.FileUploadCodeDao;
import com.oriole.motaserver.model.FileUploadCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileUploadService {

    @Resource
    FileUploadCodeDao fileUploadCodeDao;

    public String getDevice(String code){
        return fileUploadCodeDao.getDeviceIDByCode(code);
    }

    public void insertNewUploadCode(FileUploadCode fileUploadCode){
        fileUploadCodeDao.insertNewUploadCode(fileUploadCode);
    }
}
