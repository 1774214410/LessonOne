package com.liuzw.common.config.configClass;

import com.google.gson.Gson;
import com.liuzw.common.common.ErrorMsg;
import com.liuzw.common.config.exception.ApiException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 七牛 java上传文件
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/6 11:37
 */
public final class QiniuConfig {

    /**七牛账号管理ACCESSKEY*/
    private final static String ACCESSKEY = "4FA6FY_SR14BHwmPqXeHFMJb7uHIZYDpvimk_CLK";
    /**七牛账号管理SECRETKEY */
    private final static String SECRETKEY = "y74PWJoRII85yHMLMB2Dvd5jx72ETZX3Eh-kJUGX";
    /**空间名*/
    private final static String BUCKET = "repay";
    /**空间对应的域名*/
    private final static String DOMAIN_OF_BUCKET= "http://phrn2xhkp.bkt.clouddn.com/";


    private final static Logger logger = LoggerFactory.getLogger(QiniuConfig.class);

    /**
     * 构建一个上传用的Configuration对象
     * zone对象 华东(Zone.zone0()), 华北(Zone.zone1()), 华南(Zone.zone2())
     * @return
     */
    public static Configuration newConfiguration(){
        return new Configuration(Zone.zone0());
    }

    /**
     * 创建上传对象
     * @return
     */
    public static UploadManager getUploadManager(){
        return new UploadManager(newConfiguration());
    }

    /**
     * 获取上传凭证
     * @return
     */
    public static String getUpToken(){
        return Auth.create(ACCESSKEY, SECRETKEY).uploadToken(BUCKET);
    }

    /**
     * 上传文件
     * @param file  文件
     * @return  返回文件访问地址
     */
    public static StringBuilder uploadFile(MultipartFile file){
        UploadManager uploadManager = getUploadManager();
        //文件名加后缀
        String fileName = file.getOriginalFilename();
        //文件类型
        String type = fileName.substring(fileName.lastIndexOf(".")+1);
        //key为上传后的文件名，不设置会以hash值作为文件名
        String key = fileName.replace(fileName,System.currentTimeMillis()+type);
        StringBuilder builder = new StringBuilder();
        try {
            byte[] uploadBytes = file.getBytes();
            String upToken = getUpToken();
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                builder.append(DOMAIN_OF_BUCKET).append(putRet.key);
                logger.info(putRet.toString());
                logger.info("七牛返回的key-------->>>>>>"+putRet.key);
                logger.info("七牛返回的hash-------->>>>>>"+putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                logger.error(r.toString());
                try {
                    logger.error(r.bodyString());
                } catch (QiniuException ex2) {
                    logger.error(ex2.response.bodyString());
                    throw new ApiException(ErrorMsg.QINIU_UPLOAD_FAIL);
                }
            }
            return builder;
        } catch (IOException ex) {
            throw new ApiException(ErrorMsg.FILE_NO_EXIT);
        }
    }
}
