package com.liuzw.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对象返回的基类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/6 16:18
 */
@ApiModel(value = "对象返回的基类",description = "对象返回的基类")
public class ReturnBase {

    @ApiModelProperty(name = "返回代码",example = "返回代码")
    private String errcode = ErrorCode.SUCCESS_CODE;

    @ApiModelProperty(value = "返回消息", example = "返回消息")
    private String errmsg = "";

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
