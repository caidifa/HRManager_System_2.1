package com.cai.dao;

import com.cai.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 用户数据库操作接口
 */
@Repository
public interface UserDao extends BaseDao<User> {
    @Override
    int add(User user);

    @Override
    int remove(User user);

    @Override
    int update(User user);

    @Override
    List<User> find(Map map);
}
