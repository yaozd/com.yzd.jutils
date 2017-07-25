package com.yzd.jutils.aliyun.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * Created by zd.yao on 2017/7/24.
 */
public class OSSUnitTest {
    //log
    private static final Logger LOG = LoggerFactory.getLogger(OSSUnitTest.class);
    String bucketName="epolicy-hb";
    //通过文件
    @Test
    public void uploadFile(){
        //上传文件
        String flilePathName = "G:\\2017072501.pdf";
        File file = new File(flilePathName);
        String diskName = "data/pdf/";
        OSSClient client = OSSUnit.getOSSClient();
        String md5key = OSSUnit.uploadObject2OSS(client, file, bucketName, diskName);
        LOG.info("上传后的文件MD5数字唯一签名:" + md5key);  //上传后的文件MD5数字唯一签名:A30B046A34EB326C4A3BBD784333B017
    }
    //通过文件流
    @Test
    public void uploadFile2() throws FileNotFoundException {
        //上传文件
        String flilePathName = "G:\\2017072501.pdf";
        File file = new File(flilePathName);
        String fileName=file.getName();
        InputStream fileInputStream=new FileInputStream(file);
        String diskName = "data/pdf/";
        OSSClient client = OSSUnit.getOSSClient();
        String md5key = OSSUnit.uploadObject2OSS(client, fileName,fileInputStream, bucketName, diskName);
        LOG.info("上传后的文件MD5数字唯一签名:" + md5key);  //上传后的文件MD5数字唯一签名:A30B046A34EB326C4A3BBD784333B017
        //返回上传结果
        OSSUploadResult uploadResult=new OSSUploadResult();
        uploadResult.setBucketName(bucketName);
        uploadResult.setDiskName(diskName);
        uploadResult.setFileName(fileName);
    }

    /**
     * 临时授权访问
     */
    @Test
    public void generatePresignedUrl(){
        // Generate a presigned URL
        OSSClient client = OSSUnit.getOSSClient();
        Date expires = new Date (new Date().getTime() + 1000 * 60); // 1 minute to expire
        //文件相对地址
        String filePath="data/pdf/2017072501.pdf";
        //String filePath="/data/pdf/1_211.jpg";//如果是绝对地址数据会报错
        GeneratePresignedUrlRequest generatePresignedUrlRequest =new GeneratePresignedUrlRequest(bucketName, filePath);
        generatePresignedUrlRequest.setExpiration(expires);
        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
        LOG.info(url.toString());
    }
    @Test
    public void deleteFile(){
        //注：文件夹下只有一个文件，则文件夹也会一起删除；如果多个文件，只会删除指定文件名的文件
        //删除文件
        OSSClient client = OSSUnit.getOSSClient();
        String diskName = "data/pdf/";
        OSSUnit.deleteFile(client, bucketName, diskName, "2017072501.pdf");
        //console log : 删除oss-test-my下的文件datas/image/preossimg.jpg成功
    }
}
