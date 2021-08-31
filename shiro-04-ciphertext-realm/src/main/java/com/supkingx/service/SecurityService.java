package com.supkingx.service;

import java.util.Map;

/**
 * @description: 模拟数据库操作
 * @Author: wangchao
 * @Date: 2021/8/23
 */
public interface SecurityService {
    /**
     * 生成密码字符密文和salt密文
     *
     * @param loginName
     * @return
     */
    Map<String, String> findPasswordByUserName(String loginName);
}
