package com.liuzw.common.dao;

import com.liuzw.common.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/8 11:15
 */
@Mapper
public interface AccountMapper{

    /**
     * 查找单个用户
     * @param userName
     * @return
     */
    Account selectOne(@Param("userName") String userName);


}
