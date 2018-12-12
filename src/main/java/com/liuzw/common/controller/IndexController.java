package com.liuzw.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: liuzw     Email :a1774214410@163.com
 * @Date: 2018/10/19 23:32
 */
@Controller
@RequestMapping(value = "/account")
public class IndexController {

    /**
     * 访问登陆界面
     * @return
     */
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 访问首页
     * @return
     */
    @GetMapping("/toIndex")
    public String toIndex(){
        return "index";
    }

}
