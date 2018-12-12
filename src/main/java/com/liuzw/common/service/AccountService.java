package com.liuzw.common.service;

import com.liuzw.common.dto.AccountDto;
import com.liuzw.common.entity.Account;
import com.liuzw.common.vo.AccountVo;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

/**
 * @Author: liuzw     Email :a1774214410@163.com
 * @Date: 2018/10/20 16:07
 */
public interface AccountService {

    /**
     * 查找单个用户
     * @param userName
     * @return
     */
    public Account findOne(String userName);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    public Account findOneById(int id);

    /**
     * 获取所有用户
     * @return
     */
    public List<Account> getAll();

    /**
     * 添加一个用户
     * @param account
     * @return
     */
    public Account addOne(AccountDto account);

    /**
     * 上传单个文件
     * @param file
     * @return
     */
    public URL uploadFileOne(MultipartFile file);
}
