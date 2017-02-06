package com.cai.dao;

import com.cai.domain.Checking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/19.
 * <p>
 * 对考勤表的数据库操作
 */
@Repository
public interface CheckingDao extends BaseDao<Checking> {
    @Override
    int add(Checking checking);

    @Override
    int remove(Checking checking);

    @Override
    int update(Checking checking);

    @Override
    List<Checking> find(Map map);
}
