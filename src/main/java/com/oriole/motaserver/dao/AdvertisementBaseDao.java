package com.oriole.motaserver.dao;

import com.oriole.motaserver.model.AdvertisementBase;

import java.util.List;

public interface AdvertisementBaseDao {
    List<AdvertisementBase> getEffectiveAD();
}
