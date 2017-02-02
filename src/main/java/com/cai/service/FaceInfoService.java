package com.cai.service;

import com.cai.domain.FaceInfo;
import com.cai.domain.User;

import java.util.List;

/**
 * Created by caibaolong on 2017/1/12.
 * 用户业务接口
 */
public interface FaceInfoService extends BaseService<FaceInfo> {

    @Override
    boolean add(FaceInfo faceInfo);

    @Override
    boolean remove(FaceInfo faceInfo);

    @Override
    boolean update(FaceInfo faceInfo);

    @Override
    List<FaceInfo> findAll();

    @Override
    List<FaceInfo> findByIf(String ifName, String content, int id);

    /**
     * 用户查找自己的面试情况(有status条件)
     * OK
     *
     * @param user 用户
     * @param status 状态
     * @return 找到的面试情况集合
     */
    List<FaceInfo> findByUser(User user, String status);
}
