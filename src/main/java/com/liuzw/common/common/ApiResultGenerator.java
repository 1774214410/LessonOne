package com.liuzw.common.common;

/**
 * 对象返回工厂类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/6 17:30
 */
public final class ApiResultGenerator {

    /**
     * 返回对象
     * @param t  对象
     * @param <T> 类
     * @return 返回对象
     */
    public static <T> JsonResult<T> successMsg(T t){
        JsonResult<T> jsonResult = JsonResult.newInstance();
        jsonResult.setRetObj(t);
        if (t == null){
            jsonResult.setRetBoolValue(false);
        }else {
            jsonResult.setRetBoolValue(true);
        }
        return jsonResult;
    }

    /**
     * 异常信息对象
     * @param msg
     * @return 返回对象
     */
    public static  JsonResult<String> exceptionMsg(String msg){
        JsonResult<String> jsonResult = JsonResult.newInstance();
        jsonResult.setErrcode(ErrorCode.FAIL_CODE);
        jsonResult.setErrmsg(msg);
        return jsonResult;
    }

}
