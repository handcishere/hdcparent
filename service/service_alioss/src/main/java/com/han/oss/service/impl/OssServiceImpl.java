package com.han.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.han.oss.service.OssService;
import com.han.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@CrossOrigin
public class OssServiceImpl implements OssService {
    //上传头像
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try {
        // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
            InputStream inputStream = null;

            inputStream = file.getInputStream();
            String fileName=file.getOriginalFilename();

            String datepath = new DateTime().toString("yyyy/MM/dd");
            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            fileName=datepath+"/"+uuid+"_"+fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
        // 关闭OSSClient。
            ossClient.shutdown();
            String Url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return Url;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
