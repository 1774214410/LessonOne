package com.liuzw.common.service.impl;

import com.liuzw.common.config.configClass.AliyunOssConfig;
import com.liuzw.common.dao.AccountMapper;
import com.liuzw.common.dto.AccountDto;
import com.liuzw.common.entity.Account;
import com.liuzw.common.respository.AccountJpa;
import com.liuzw.common.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

/**
 * @Author: liuzw     Email :a1774214410@163.com
 * @Date: 2018/10/20 16:07
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpa accountJpa;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findOne(String userName) {
        return accountMapper.selectOne(userName);
    }

    @Override
    public Account findOneById(int id) {
        return accountJpa.findAccountById(id);
    }

    /**
     * 获取所有用户
     * @return
     */
    @Override
    public List<Account> getAll() {
        return accountJpa.findAll();
    }

    /**
     * 添加一个用户
     * @return
     */
    @Override
    public Account addOne(AccountDto dto) {
        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setPwd(dto.getPwd());
        return accountJpa.save(account);
    }

    /**
     * 上传单个文件到OSS
     * @param file
     * @return
     */
    @Override
    public URL uploadFileOne(MultipartFile file) {
        URL url = AliyunOssConfig.uploadFile(file);
        return url;
    }
}
