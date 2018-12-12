package com.liuzw.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义的数组值验证注解
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/9 11:41
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ValuesCheckValidator.class)
public @interface ValuesCheck {

    String values();

    String message() default "类型输入错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
