package com.oriole.motaserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.oriole.motaserver.Constant;
import com.oriole.motaserver.Entity.MsgEntity;
import com.oriole.motaserver.WebSocket.UploadFileWSServer;
import com.oriole.motaserver.model.FileUploadCode;
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
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/file")
@CrossOrigin
@EnableAutoConfiguration
@Controller
public class FileIOController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/printFileUpload")
    public String upload(Map<String, Object> paramMap, @RequestParam String randomCode, @RequestParam String deviceID) {

        paramMap.put("DeviceName", deviceService.getDevice(deviceID));
        paramMap.put("DeviceID", deviceID);
        paramMap.put("RandomCode",randomCode);

        MsgEntity msgEntity=new MsgEntity("SUCCESS","1","连接成功！");
        UploadFileWSServer.pushBySys(randomCode, JSON.toJSONString(msgEntity));

        return "print_file_upload";
    }

    @RequestMapping(value = "/doPrintFileUpload",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doPrintFileUpload(@RequestParam(value = "uploadFile", required = true) MultipartFile uploadFile,
                         @RequestParam String randomCode, @RequestParam String deviceID) {
        if (uploadFile.isEmpty()) {
            MsgEntity msgEntity=new MsgEntity("ERROR","-1","上传失败，请选择文件!");
            return JSON.toJSONString(msgEntity);
        }

        //获取文件名
        String fileName = uploadFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成新文件名
        fileName = deviceID+"_"+randomCode+suffixName;

        File path = null;
        //获取根目录
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        }catch (FileNotFoundException e){
            if(!path.exists()) {
                path = new File("");}
        }

        File upload = new File(path.getAbsolutePath(),"static/PrintFileUploadTemp/");
        if(!upload.exists()) {
            upload.mkdirs();
        }
        File file = new File(upload.getAbsolutePath()+"/"+ fileName);
        try {
            uploadFile.transferTo(file);

            MsgEntity FileUrl=new MsgEntity("SUCCESS","2",fileName);
            UploadFileWSServer.pushBySys(randomCode, JSON.toJSONString(FileUrl));

            MsgEntity msgEntity=new MsgEntity("SUCCESS","1","上传成功！");
            return JSON.toJSONString(msgEntity);
        } catch (Exception e) {
            System.out.println(e.toString());
            MsgEntity msgEntity=new MsgEntity("ERROR","-2",e.toString());
            return JSON.toJSONString(msgEntity);
        }
    }

    //实现Spring Boot 的文件下载功能(被下载)，映射网址为/download
    @RequestMapping("/doDownload")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam String fileName, @RequestParam String location)
            throws UnsupportedEncodingException {
        // 如果文件名不为空，则允许下载进行
        if (fileName != null) {
            //设置文件路径
            File rootpath = null;
            //获取根目录
            try {
                rootpath = new File(ResourceUtils.getURL("classpath:").getPath());
            }catch (FileNotFoundException e){
            }
            File uploadPath = new File(rootpath.getAbsolutePath(),"static/"+location+"/");
            File file = new File(uploadPath.getAbsolutePath(), fileName);
            // 如果文件名存在，则允许下载开始
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download Successfully!");
                } catch (Exception e) {
                    System.out.println("Download Failed!");
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                System.out.println(file.getAbsoluteFile()+"文件不存在！");
            }
        }
        return null;
    }


    @RequestMapping(value = "/getPrintFileUploadQRCode",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getPrintFileUploadQRCode(@RequestParam String randomCode,@RequestParam String deviceID) {
        byte[] qrCode = null;

        try {
            FileUploadCode fileUploadCode = new FileUploadCode(randomCode, deviceID, new Date());
            fileUploadService.insertNewUploadCode(fileUploadCode);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(Constant.upload_file_url + "randomCode=" + randomCode + "&deviceID=" + deviceID, BarcodeFormat.QR_CODE, 900, 900);
            System.out.println(Constant.upload_file_url + "randomCode=" + randomCode + "&deviceID=" + deviceID);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            qrCode = pngOutputStream.toByteArray();
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(qrCode, headers, HttpStatus.CREATED);
    }
}
