package com.liuzw.common.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 文件不为空验证执行类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/9 15:45
 */
public class EmptyFileValidator implements ConstraintValidator<EmptyFile,MultipartFile> {

    @Override
    public void initialize(EmptyFile constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file == null || file.getSize() == 0){
            return false;
        }
        return true;
    }
}
