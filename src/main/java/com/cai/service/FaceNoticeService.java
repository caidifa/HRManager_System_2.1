package com.cai.service;

import com.cai.domain.FaceNotice;
import com.cai.domain.User;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 用户业务接口
 */
public interface FaceNoticeService extends BaseService<FaceNotice> {

    @Override
    boolean add(FaceNotice faceNotice);

    @Override
    boolean remove(FaceNotice faceNotice);

    @Override
    boolean update(FaceNotice faceNotice);

    @Override
    List<FaceNotice> findAll();

    @Override
    List<FaceNotice> findByIf(String ifName, String content, int id);

    List<FaceNotice> findByMap(Map<String, Object> map);

    /**
     * 创建面试通知
     *
     * @param faceNotice 新建的面试通知
     * @return 新建情况
     * @throws ParseException 时间转化异常
     */
    Map<String, Object> addByCreate(FaceNotice faceNotice) throws ParseException;

    /**
     * 用户查找自己的面试通知(status条件)
     *
     * @param user   用户
     * @param status 状态
     * @return 找到的面试通知集合
     */
    List<FaceNotice> findByUser(User user, String status);

    /**
     * 得到详细的面试通知
     *
     * @param faceNotice 面试通知
     * @return 详细的面试通知
     */
    FaceNotice findDetail(FaceNotice faceNotice);

}
