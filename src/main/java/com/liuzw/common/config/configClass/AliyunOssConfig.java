package com.liuzw.common.config.configClass;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * java使用阿里云OSS存储对象上传图片
 * 文档地址URL:https://www.aliyun.com/jiaocheng/796140.html
 * Email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/5 17:54
 */
public final class AliyunOssConfig {

    /**Endpoint以杭州为例，其它Region请按实际情况填写*/
    private static final String  ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";

    /**阿里云主账号AccessKey拥有所有API的访问权限ACCESS_KEY_ID*/
    private static final String ACCESS_KEY_ID = "LTAIt6jQot1eRadb";

    /**阿里云主账号AccessKey拥有所有API的访问权限ACCESS_KEY_SECRET*/
    private static final String ACCESS_KEY_SECRET = "pIW8sWfe8vtx2qiMExLPivAL8pEryg";

    /**创建的空间名称*/
    private static final String BUCKET_NAME = "ssumday";

    /**文件夹名*/
    private static final String FOLDER = "image/";

    private final static Logger logger = LoggerFactory.getLogger(AliyunOssConfig.class);

    /**
     * 创建OSSClient实例
     * @return
     */
    public static OSSClient createClient(){
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID,ACCESS_KEY_SECRET);
    }

    /**
     * 关闭OSSClient
     * @param ossClient
     */
    public static void shutDown(OSSClient ossClient){
        ossClient.shutdown();
    }

    /**
     * 创建存储空间
     * @param ossClient
     * @param bucketName
     * @return
     */
    public static String createBucketName(OSSClient ossClient,String bucketName){
        //存储空间
        final String bucketNames=bucketName;
        if(!ossClient.doesBucketExist(bucketName)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(bucketName);
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间
     * @param ossClient
     * @param bucketName
     */
    public static boolean deleteBucket(OSSClient ossClient, String bucketName){
        if(ossClient.doesBucketExist(bucketName)){
            //删除存储空间
            ossClient.deleteBucket(bucketName);
            return true;
        }
        return false;
    }

    /**
     * 上传文件
     * @param file
     */
    public static URL uploadFile(MultipartFile file){
        OSSClient ossClient = createClient();
        URL url = null;
        try {
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(file.getSize());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME,定义文件的类型及网页编码,决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成,
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(file.getContentType());
            //指定该Object被下载时的名称(指示MINME用户代理如何显示附加的文件,打开或下载,及文件名称)
            String fileName = file.getOriginalFilename();
            String type=fileName.substring(fileName.lastIndexOf(".")+1);
            int random = (int)(Math.random()*900)+10000;
            String fileNameNew = fileName.replace(fileName, String.valueOf(System.currentTimeMillis())+String.valueOf(random)+"."+type);
            //指定该Object被下载时的名称
            metadata.setContentDisposition("filename/filesize=" + fileNameNew + "/" + file.getSize() + "Byte.");
            //上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(BUCKET_NAME, FOLDER + fileNameNew, file.getInputStream(), metadata);
            //解析结果
            url = getUrl(ossClient,putResult.getETag());
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 创建文件夹(注意创建文件夹的时候要在文件名后面添加”/“)
     * @param ossClient
     * @param bucketName
     * @param folder
     * @return
     */
    public static String createFolder(OSSClient ossClient,String bucketName,String folder){
        //文件夹名
        final String keySuffixWithSlash =folder;
        //判断文件夹是否存在,不存在则创建
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash+"/")){
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash+"/", new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash+"/");
            String fileDir=object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除文件
     * @param ossClient
     * @param bucketName
     * @param folder
     * @param key
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName
     * @return
     */
    public static String getContentType(String fileName) {
        //文件的后缀名例如 test.jpg
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    /**
     * 获取上传后的URL
     * @param ossClient
     * @param key
     * @return
     */
    public static URL getUrl(OSSClient ossClient, String key) {
        // 设置URL过期时间为10年
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl("ssumday", key, expiration);
        return url;
    }

    public static void main(String[] args){
        OSSClient ossClient = createClient();
       String folder = createFolder(ossClient,BUCKET_NAME,"image3");
       System.out.println(folder);
       ossClient.shutdown();
    }
}
