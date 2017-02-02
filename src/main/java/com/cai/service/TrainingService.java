package com.cai.service;

import com.cai.domain.Training;
import com.cai.domain.TrainingInfo;

import java.text.ParseException;
import java.util.List;

/**
 * Created by caibaolong on 2017/1/12.
 * 培训的业务基本接口
 */
public interface TrainingService extends BaseService<Training> {
    @Override
    boolean add(Training training);

    @Override
    boolean remove(Training training);

    @Override
    boolean update(Training training);

    @Override
    List<Training> findAll();

    @Override
    List<Training> findByIf(String ifName, String content, int id);

    /**
     * 员工查找自己相关的已发布的培训信息
     *
     * @param eid 员工id
     * @return 培训信息的集合 size为0说明没有
     */
    List<TrainingInfo> findAllByEid(int eid);

    /**
     * 员工查找自己相关的已发布的培训通知 ps,培训详情的开始日期要大于当前日期
     *
     * @param eid 员工id
     * @return 培训信息的集合 size为0说明没有
     */
    List<TrainingInfo> findNowByEid(int eid) throws ParseException;
}
