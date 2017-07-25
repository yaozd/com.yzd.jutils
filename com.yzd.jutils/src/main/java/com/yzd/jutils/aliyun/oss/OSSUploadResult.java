package com.yzd.jutils.aliyun.oss;

import lombok.Data;

/**
 * Created by zd.yao on 2017/7/25.
 */
@Data
public class OSSUploadResult {
    private String bucketName;
    private String diskName;
    private String fileName;
    //md5的值用于以后文件的验证
    private String md5Value;
}
