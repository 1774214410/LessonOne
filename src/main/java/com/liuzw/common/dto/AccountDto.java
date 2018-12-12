package com.liuzw.common.dto;

import javax.validation.constraints.NotBlank;

/**
 * Email :a1774214410@163.com
 *
 * @author: liuzw
 * @date: 2018/10/27 11:17
 */
public class AccountDto {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
