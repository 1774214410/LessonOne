package com.liuzw.common.service.impl;

import com.liuzw.common.entity.Account;
import com.liuzw.common.respository.TestJpa;
import com.liuzw.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/11 13:53
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestJpa testJpa;

    /**
     * 添加一个用户
     * @param account
     * @return
     */
    @Override
    public Account addOne(Account account) {
        return testJpa.save(account);
    }
}
