package com.tobeyond.blog.controller.admin.pc;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.tobeyond.blog.config.QiniuConfig;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller("uploadController")
@RequestMapping(value = "/admin/upload")
public class UploadController {

    @Autowired
    QiniuConfig qiniuConfig;

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public ReturnJson uploadFile(@RequestParam(value = "file") MultipartFile multipartFile
    ) throws IOException {
        ReturnJson returnJson;
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();

        String originalFilename = multipartFile.getOriginalFilename();
        int begin = originalFilename.indexOf(".");
        int end = originalFilename.length();
        StringBuilder fileName = new StringBuilder(String.valueOf(DateKit.getCurrentUnixTime()));
        fileName.append(originalFilename.substring(begin, end));


        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        String upToken = auth.uploadToken(qiniuConfig.getBucket());
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Response response = uploadManager.put(fileInputStream, fileName.toString(), upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            Map data = new HashMap();
            data.put("key", putRet.key);
            data.put("hash", putRet.hash);

            returnJson = ReturnJson.success("上传成功");
            returnJson.setData(data);
        } catch (QiniuException ex) {
            Response r = ex.response;
            returnJson = ReturnJson.error(r.toString());
        }

        return returnJson;
    }

}
