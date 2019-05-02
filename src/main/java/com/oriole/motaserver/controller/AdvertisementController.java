package com.oriole.motaserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.oriole.motaserver.Constant;
import com.oriole.motaserver.Entity.MsgEntity;
import com.oriole.motaserver.WebSocket.UploadFileWSServer;
import com.oriole.motaserver.model.AdvertisementBase;
import com.oriole.motaserver.model.AdvertisementUpdateFlag;
import com.oriole.motaserver.model.FileUploadCode;
import com.oriole.motaserver.service.AdvertisementService;
import com.oriole.motaserver.service.DeviceService;
import com.oriole.motaserver.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/Advertisement")
@CrossOrigin
@EnableAutoConfiguration
@Controller
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/updateAD",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateAD(@RequestParam String deviceID) throws Exception{
        int ADUpdateFlag=advertisementService.getADUpdateFlagByID(deviceID);
        if(ADUpdateFlag==0){
            JSONArray adFilePath = new JSONArray();
            ArrayList<AdvertisementBase> advertisementBases = (ArrayList)advertisementService.getEffectiveAD();
            for (AdvertisementBase adInfo : advertisementBases) {
                adFilePath.add(adInfo.getAdFile());
            }
            advertisementService.setADUpdateFlagByID(new AdvertisementUpdateFlag(deviceID,(byte) 1,new Date()));
            MsgEntity msgEntity=new MsgEntity("SUCCESS","1",adFilePath.toJSONString());
            return JSON.toJSONString(msgEntity);
        }else {
            MsgEntity msgEntity=new MsgEntity("SUCCESS","0","无更新信息！");
            return JSON.toJSONString(msgEntity);
        }
    }
}

