package com.wzy.proxy;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/5/30
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
  interface  UserManager {

     void addUser(String userId, String userName);

     void delUser(String userId) ;

     String findUser(String userId);

     void modifyUser(String userId, String userName);
}
