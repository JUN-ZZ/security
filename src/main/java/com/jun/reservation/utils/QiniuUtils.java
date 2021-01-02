package com.jun.reservation.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.util.UUID;

/**
 * @author jun
 * @date 2021/1/1
// */
@Data
@org.springframework.context.annotation.Configuration
public class QiniuUtils {

    /**
     * 七牛域名domain
     */
    @Value("${oss.qiniu.domain}")
    private String domain;

    /**
     * 七牛ACCESS_KEY
     */
    @Value("${oss.qiniu.accessKey}")
    private String accessKey;
    /**
     * 七牛SECRET_KEY
     */
    @Value("${oss.qiniu.secretKey}")
    private String secretKey;
    /**
     * 七牛空间名
     */
    @Value("${oss.qiniu.bucketName}")
    private String bucketName;


    //     密钥
//    private Auth auth = Auth.create(accessKey, secretKey);

    public QiniuUtils(){
    }


    /**
     * 将图片上传到七牛云
     */
    public String uploadQNImg(FileInputStream file, String key) throws QiniuException {

        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = domain + "/" + putRet.key;
                // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                    throw ex;
                }
                throw ex;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @Description: 生成唯一图片名称
     * @Param: fileName
     * @return: 云服务器fileName
     */
    public static String getRandomImgName(String fileName) {
        String suffix;

        if ((fileName == null || fileName.isEmpty())){
            suffix = ".jpg";
        }else {
            int index = fileName.lastIndexOf(".");
            suffix = index !=-1 ? fileName.substring(index): ".jpg";
        }
        // 获取文件后缀
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 生成上传至云服务器的路径
        String path = uuid + suffix;
        return path;
    }
}

