package com.oriole.motaserver.service;

import com.oriole.motaserver.dao.AdvertisementBaseDao;
import com.oriole.motaserver.dao.AdvertisementUpdateFlagDao;
import com.oriole.motaserver.model.AdvertisementBase;
import com.oriole.motaserver.model.AdvertisementUpdateFlag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvertisementService {

    @Resource
    AdvertisementBaseDao advertisementBaseDao;
    @Resource
    AdvertisementUpdateFlagDao advertisementUpdateFlagDao;

    public List<AdvertisementBase> getEffectiveAD(){
        return advertisementBaseDao.getEffectiveAD();
    }

    public int getADUpdateFlagByID(String DeviceID){
        return advertisementUpdateFlagDao.getADUpdateFlagByID(DeviceID);
    }

    public void setADUpdateFlagByID(AdvertisementUpdateFlag advertisementUpdateFlag){
        advertisementUpdateFlagDao.setADUpdateFlagByID(advertisementUpdateFlag);
    }
}
