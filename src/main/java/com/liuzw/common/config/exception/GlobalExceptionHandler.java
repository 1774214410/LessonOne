package com.liuzw.common.config.exception;

import com.liuzw.common.common.ApiResultGenerator;
import com.liuzw.common.common.ErrorMsg;
import com.liuzw.common.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/7 14:22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理通用异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    JsonResult<String> handlerException(Exception e){
        logger.error(e.getMessage(),e);
        return ApiResultGenerator.exceptionMsg(ErrorMsg.SERVER_INTERNAL_ERROR);
    }

    /**
     * 返回业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(ApiException.class)
    JsonResult<String> handlerApiException(ApiException e){
        logger.error(e.getMessage(),e);
        return ApiResultGenerator.exceptionMsg(e.getMessage());
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    JsonResult<String> handleValidException(ConstraintViolationException e){
        logger.error(e.getMessage(),e);
        return ApiResultGenerator.exceptionMsg(e.getConstraintViolations().iterator().next().getMessage());
    }

}
