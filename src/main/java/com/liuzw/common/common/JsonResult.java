package com.liuzw.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返回的对象包装类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/6 16:21
 */
@ApiModel(value = "返回的对象包装类",description = "返回的对象包装类")
public class JsonResult<T> extends ReturnBase implements Serializable {

    private static final long serialVersionUID = -4512061212458890987L;

    /**构造函数私有化*/
    private JsonResult(){}

    /**访问点*/
    public static <T> JsonResult<T> newInstance(){
        return new JsonResult<T>();
    }

    @ApiModelProperty(name = "返回的对象",example = "返回的对象")
    private T retObj = null;

    @ApiModelProperty(value = "返回查找对象时存在与否",example = "返回查找对象时存在与否")
    private boolean retBoolValue = false;

    public T getRetObj() {
        return retObj;
    }

    public void setRetObj(T retObj) {
        this.retObj = retObj;
    }

    public boolean isRetBoolValue() {
        return retBoolValue;
    }

    public void setRetBoolValue(boolean retBoolValue) {
        this.retBoolValue = retBoolValue;
    }
}
