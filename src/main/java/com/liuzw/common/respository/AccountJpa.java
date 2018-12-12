package com.liuzw.common.respository;

import com.liuzw.common.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

/**
 * @Author: liuzw     Email :a1774214410@163.com
 * @Date: 2018/10/20 16:03
 */
public interface AccountJpa extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account>, Serializable {

    /**
     * 查找用户
     * @param username
     * @return
     */
    @Query(value = "select id,user_name,password from account where user_name = ?1",nativeQuery = true)
    Account findAccountByUsername(String username);

    /**
     * 查找单个用户
     * @param id
     * @return
     */
    Account findAccountById(int id);

}
