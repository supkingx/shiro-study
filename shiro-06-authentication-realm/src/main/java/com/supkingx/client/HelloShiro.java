package com.supkingx.client;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 权限、角色的使用
 */
public class HelloShiro {
    public static void main(String[] args) {
        Subject subject = doLogin();
        System.out.println("是否登录成功:" + subject.isAuthenticated());

        // 校验角色
        boolean admin = subject.hasRole("admin");
        System.out.println("是否有管理员角色:" + admin);

        try {
            subject.checkRole("coder");
            System.out.println("当前用户有coder角色");
        } catch (Exception e) {
            System.out.println("当前用户没有coder角色");
        }

        // 校验权限
        boolean permitted = subject.isPermitted("order:list");
        System.out.println("是否有查看order权限:" + permitted);

        try {
            subject.checkPermission("order:update");
            System.out.println("当前用户有编辑权限");
        } catch (Exception e) {
            System.out.println("当前用没有有编辑权限");
        }
    }

    private static Subject doLogin() {
        //导入权限ini文件构建权限工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //工厂构建安全管理器
        SecurityManager securityManager = factory.getInstance();
        //使用SecurityUtils工具生效安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //使用SecurityUtils工具获得主体
        Subject subject = SecurityUtils.getSubject();
        //构建账号token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("jay", "123");
        //登录操作
        subject.login(usernamePasswordToken);
        return subject;
    }
}