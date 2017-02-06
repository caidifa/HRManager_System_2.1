package com.cai.dao;

import com.cai.domain.Training;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 培训<-->员工关联数据库操作接口
 */
@Repository
public interface TrainingDao extends BaseDao<Training> {
    @Override
    int add(Training training);

    @Override
    int remove(Training training);

    @Override
    int update(Training training);

    @Override
    List<Training> find(Map map);
}
