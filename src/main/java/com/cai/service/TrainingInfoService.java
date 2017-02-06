package com.cai.service;

import com.cai.domain.TrainingInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 培训详情的业务基本接口
 */
public interface TrainingInfoService extends BaseService<TrainingInfo> {
    @Override
    boolean add(TrainingInfo trainingInfo);

    @Override
    boolean remove(TrainingInfo trainingInfo);

    @Override
    boolean update(TrainingInfo trainingInfo);

    @Override
    List<TrainingInfo> findAll();

    @Override
    List<TrainingInfo> findByIf(String ifName, String content, int id);

    /**
     * 管理员添加培训
     *
     * @param trainingInfo 需要添加的培训信息
     * @return 添加时的情况
     * @throws ParseException 日期转化异常
     */
    Map<String, Object> addTrainAdmin(TrainingInfo trainingInfo) throws ParseException;

    /**
     * 管理员发布一条培训
     *
     * @param trainingInfo 需要发布的培训信息
     * @return 发布时的情况
     * @throws ParseException 日期转化异常
     */
    Map<String, Object> updateByPostOne(TrainingInfo trainingInfo) throws ParseException;

    /**
     * 管理员修改培训
     *
     * @param trainingInfo 修改好的培训信息
     * @return 修改时的情况
     * @throws ParseException 日期转化异常
     */
    Map<String, Object> updateByEdit(TrainingInfo trainingInfo) throws ParseException;

}
