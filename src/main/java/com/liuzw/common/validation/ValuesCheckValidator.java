package com.liuzw.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义的数组值验证执行类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/9 11:45
 */
public class ValuesCheckValidator implements ConstraintValidator<ValuesCheck,String> {

    private String values;

    /**
     * 验证的数据初始化
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValuesCheck constraintAnnotation) {
        values = constraintAnnotation.values();
    }

    /**
     * 实现验证
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean flag = false;
        String[] valuesarr = values.split(",");
        for (int i = 0;i<valuesarr.length;i++){
            if (value.equals(valuesarr[i])){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
