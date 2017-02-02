package com.cai.dao;

import com.cai.domain.FaceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 面试情况数据库操作接口
 */
@Repository
public interface FaceInfoDao extends BaseDao<FaceInfo> {
    @Override
    int add(FaceInfo faceInfo);

    @Override
    int remove(FaceInfo faceInfo);

    @Override
    int update(FaceInfo faceInfo);

    @Override
    List<FaceInfo> find(Map map);
}
