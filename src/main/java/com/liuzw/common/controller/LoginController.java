package com.liuzw.common.controller;


import com.liuzw.common.common.ApiResultGenerator;
import com.liuzw.common.common.ErrorMsg;
import com.liuzw.common.common.JsonResult;
import com.liuzw.common.config.configClass.AppConfig;
import com.liuzw.common.config.exception.ApiException;
import com.liuzw.common.dto.AccountDto;
import com.liuzw.common.entity.Account;
import com.liuzw.common.service.AccountService;
import com.liuzw.common.validation.ValuesCheck;
import com.liuzw.common.vo.AccountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/10/27 14:03
 */
@Api(value = "用户登陆",description = "用户登陆")
@RestController
@RequestMapping("/user")
@Validated
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppConfig appConfig;

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pwd",value = "密码",required = true,paramType = "query")
    })
    @PostMapping("/login")
    public JsonResult<Account> login(@Valid AccountDto dto){
        String userName = dto.getUsername();
        String pwd= dto.getPwd();
        Account account = accountService.findOne(userName);
        //用户不存在
        if (account == null){
            throw new ApiException(ErrorMsg.USER_NO_EXIT);
        }else if(!pwd.equals(account.getPwd())){
            throw new ApiException(ErrorMsg.PASSWORD_WRONG);
        }
        return ApiResultGenerator.successMsg(account);
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping(value = "/getAll")
    public List<Account> getAll(){
        logger.info("获取所有的用户");
        return accountService.getAll();
    }

    /**
     * 获取单个用户
     * @param id
     * @return
     */
    @ApiOperation("获取单个用户")
    @ApiImplicitParam(name = "id",value = "用户Id",required = true,paramType = "query")
    @GetMapping("/findOne")
    public JsonResult<Account> selectOne(@ValuesCheck(values = "1,2,3") String id){
        logger.info("热部署的测试进行重启生效！！！");
        return ApiResultGenerator.successMsg(accountService.findOneById(Integer.parseInt(id)));
    }

    /**
     * 添加一个用户
     * @param account
     * @return
     */
    @ApiOperation(value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pwd",value = "密码",required = true,paramType = "query")
    })
    @PostMapping(value = "/addOne")
    public JsonResult<Account> addOne(@Valid AccountDto account){
        return ApiResultGenerator.successMsg(accountService.addOne(account));
    }

    @GetMapping("getConfig")
    public String getConfig(){
        return appConfig.getUsername();

    }
}
