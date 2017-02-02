package com.cai.dao;

import com.cai.domain.TrainingInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 培训详情数据库操作接口
 */
@Repository
public interface TrainingInfoDao extends BaseDao<TrainingInfo> {
    @Override
    int add(TrainingInfo trainingInfo);

    @Override
    int remove(TrainingInfo trainingInfo);

    @Override
    int update(TrainingInfo trainingInfo);

    @Override
    List<TrainingInfo> find(Map map);
}
