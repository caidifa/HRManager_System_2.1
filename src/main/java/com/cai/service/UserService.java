package com.cai.service;

import com.cai.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 用户业务接口
 */
public interface UserService extends BaseService<User> {

    boolean add(User user);

    boolean remove(User user);

    boolean update(User user);

    List<User> findAll();

    List<User> findByIf(String ifName, String content, int id);

    /**
     * 用户登陆 方式: ⑴邮箱 ⑵手机号
     *
     * @param user 用户登录时输入的信息
     * @return 回馈登陆判断的信息
     */
    Map<String, Object> findByLogin(User user);

    /**
     * 用户注册 方式: ⑴邮箱 ⑵手机号
     *
     * @param user 用户注册时输入的信息
     * @return 回馈注册是否成功的信息
     */
    Map<String, Object> addByRegister(User user);

    /**
     * 用户登陆后可以修改个人信息,但要避免与其他用户重复
     *
     * @param user 待修改的用户
     * @return 回馈修改的信息
     */
    Map<String, Object> updateUser(User user);

}
