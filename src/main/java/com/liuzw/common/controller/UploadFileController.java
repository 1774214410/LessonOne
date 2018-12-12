package com.liuzw.common.controller;

import com.liuzw.common.common.JsonResult;
import com.liuzw.common.common.ApiResultGenerator;
import com.liuzw.common.config.configClass.QiniuConfig;
import com.liuzw.common.service.AccountService;
import com.liuzw.common.validation.EmptyFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

/**
 * Email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/5 21:11
 */
@Api(description = "文件上传")
@RestController
@RequestMapping("/file")
@Validated
public class UploadFileController {

    @Autowired
    private AccountService accountService;

    /**
     * 上传文件到云存储OSS
     * @param file
     */
    @ApiOperation(value = "上传到云存储OSS",notes = "上传到云存储OSS")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",required = true,dataType = "file",paramType = "form")
    })
    @RequestMapping(value = "/uploadFileOss",method = RequestMethod.POST)
    public JsonResult<URL> uploadFileOss(@EmptyFile MultipartFile file){
        return ApiResultGenerator.successMsg(accountService.uploadFileOne(file));
    }

    /**
     * 上传文件到七牛云
     * @param file
     */
    @ApiOperation(value = "上传到七牛云",notes = "上传到七牛云")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",required = true,dataType = "file",paramType = "form")
    })
    @RequestMapping(value = "/uploadFileQiniu",method = RequestMethod.POST)
    public JsonResult<StringBuilder> uploadFileQiniu(@EmptyFile MultipartFile file){
        return ApiResultGenerator.successMsg(QiniuConfig.uploadFile(file));
    }



}
