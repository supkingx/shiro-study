package com.supkingx.service;

import com.supkingx.utils.DigestsUtil;

import java.util.Map;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/23
 */
public class SecurityServiceImpl implements SecurityService {

    @Override
    public Map<String, String> findPasswordByUserName(String loginName) {
        // 模拟数据库拿到的密文 密码
        return DigestsUtil.encryptPassword("123");
    }

}
