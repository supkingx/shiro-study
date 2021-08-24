package com.supkingx.service;

/**
 * @description: 模拟数据库操作
 * @Author: wangchao
 * @Date: 2021/8/23
 */
public interface SecurityService {

    String findPasswordByUserName(String loginName);
}
