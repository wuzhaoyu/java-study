package com.wzy.proxy;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/5/30
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class Test {

    public static void main(String[] args) {

        //静态代理 手动创建代理类 在程序运行前代理类的.class文件就已经存在
        UserManager userManager  =  (UserManager)new UserManagerImplProxy(new UserManagerImpl());
        userManager.addUser("1212","ceshi");
    }
}
