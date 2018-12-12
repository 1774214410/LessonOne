package com.liuzw.common.controller;

import com.github.pagehelper.PageHelper;
import com.liuzw.common.common.ApiResultGenerator;
import com.liuzw.common.common.JsonResult;
import com.liuzw.common.entity.Account;
import com.liuzw.common.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/11 13:49
 */
@Api(description = "测试")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "添加一个用户")
    @RequestMapping(value = "/addOne",method = RequestMethod.GET)
    public JsonResult<Account> addOne(){
        PageHelper pageHelper = new PageHelper();
        Account account = new Account();
        account.setUsername("斯大林开始");
        account.setPwd("123456");
        return ApiResultGenerator.successMsg(testService.addOne(account));
    }


}
