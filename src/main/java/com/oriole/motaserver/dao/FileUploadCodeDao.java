package com.oriole.motaserver.dao;

import com.oriole.motaserver.model.FileUploadCode;

public interface FileUploadCodeDao {
    void insertNewUploadCode(FileUploadCode fileUploadCode);
    String getDeviceIDByCode(String code);
}
