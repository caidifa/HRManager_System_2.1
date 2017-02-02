package com.cai.dao;

import com.cai.domain.HireInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/13.
 * 招聘信息数据库操作接口
 */
@Repository
public interface HireInfoDao extends BaseDao<HireInfo> {
    @Override
    int add(HireInfo hireInfo);

    @Override
    int remove(HireInfo hireInfo);

    @Override
    int update(HireInfo hireInfo);

    @Override
    List<HireInfo> find(Map map);
}
