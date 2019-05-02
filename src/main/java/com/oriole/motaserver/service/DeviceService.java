package com.oriole.motaserver.service;

import com.oriole.motaserver.dao.DeviceBaseDao;
import com.oriole.motaserver.dao.FileUploadCodeDao;
import com.oriole.motaserver.model.FileUploadCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceService {

    @Resource
    DeviceBaseDao deviceBaseDao;

    public String getDevice(String DeviceID){
        return deviceBaseDao.getDeviceNameByID(DeviceID);
    }

}
