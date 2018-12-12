package com.liuzw.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 文件不为空验证注解
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/9 15:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = EmptyFileValidator.class)
public @interface EmptyFile {

    String message() default "文件不能为空";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
