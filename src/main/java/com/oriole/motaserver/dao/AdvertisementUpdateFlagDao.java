package com.oriole.motaserver.dao;

import com.oriole.motaserver.model.AdvertisementBase;
import com.oriole.motaserver.model.AdvertisementUpdateFlag;

public interface AdvertisementUpdateFlagDao {
    int getADUpdateFlagByID(String deviceID);
    void setADUpdateFlagByID(AdvertisementUpdateFlag advertisementUpdateFlag);
}
