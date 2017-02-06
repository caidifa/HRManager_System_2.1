package com.cai.service.impl;

import com.cai.dao.UserDao;
import com.cai.domain.User;
import com.cai.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 用户业务接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public boolean add(User user) {
        return userDao.add(user) > 0;
    }

    public boolean remove(User user) {
        return userDao.remove(user) > 0;
    }

    public boolean update(User user) {
        return userDao.update(user) > 0;
    }

    public List<User> findAll() {
        return userDao.find(null);
    }

    public List<User> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return userDao.find(map);
    }

    /**
     * 用户登陆 方式: ⑴邮箱 ⑵手机号
     * OK
     *
     * @param user 用户登录时输入的信息
     * @return 登陆情况的信息success:user/fail:message
     */
    public Map<String, Object> findByLogin(User user) {
        Map<String, Object> map = new HashedMap();
        List<User> users;
        //1.通过邮箱登陆
        if (user.getEmail() != null) {
            users = findByIf("email", user.getEmail(), 0);
            if (users.size() == 0) {
                map.put("fail", "该邮箱还未注册!");
            } else if (!users.get(0).getPassword().equals(user.getPassword())) {
                map.put("fail", "密码有误!");
            } else {
                map.put("success", users.get(0));
            }
        }
        //2.通过手机号登陆
        if (user.getPhone() != null) {
            users = findByIf("phone", user.getPhone(), 0);
            if (users.size() == 0) {
                map.put("fail", "该手机号还未注册!");
            } else if (!users.get(0).getPassword().equals(user.getPassword())) {
                map.put("fail", "密码有误!");
            } else {
                map.put("success", users.get(0));
            }
        }
        return map;
    }

    /**
     * 用户注册 方式: ⑴邮箱 ⑵手机号
     * OK
     *
     * @param user 用户注册时输入的信息
     * @return 注册是否成功的信息 success / fail + message
     */
    public Map<String, Object> addByRegister(User user) {
        Map<String, Object> map = new HashedMap();
        //1.通过邮箱注册
        if (user.getEmail() != null) {
            if (findByIf("email", user.getEmail(), 0).size() > 0) {
                map.put("fail", "该邮箱已注册!");
            } else {
                add(user);
                map.put("success", "邮箱注册成功!");
            }
        }
        //2.通过手机号注册
        if (user.getPhone() != null) {
            if (findByIf("phone", user.getPhone(), 0).size() > 0) {
                map.put("fail", "该手机号已注册!");
            } else {
                add(user);
                map.put("success", "手机号注册成功!");
            }
        }
        return map;
    }

    /**
     * 用户登陆后可以修改个人信息,但要避免与其他用户重复
     * OK
     *
     * @param user 待修改的用户
     * @return 回馈修改的信息success/fail:message
     */
    public Map<String, Object> updateUser(User user) {
        Map<String, Object> map = new HashedMap();
        List<User> users = findByIf("email", user.getEmail(), 0);
        if (users.size() > 0 && users.get(0).getId() != user.getId()) {
            map.put("fail", "该邮箱已使用!");
            return map;
        }
        users = findByIf("phone", user.getPhone(), 0);
        if (findByIf("phone", user.getPhone(), 0).size() > 0 && users.get(0).getId() != user.getId()) {
            map.put("fail", "该手机号已使用!");
            return map;
        }
        update(user);
        map.put("success", "修改成功!");
        return map;
    }


}
