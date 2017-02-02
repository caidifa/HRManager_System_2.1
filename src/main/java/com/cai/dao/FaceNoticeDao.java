package com.cai.dao;

import com.cai.domain.FaceNotice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 面试通知数据库操作接口
 */
@Repository
public interface FaceNoticeDao extends BaseDao<FaceNotice> {
    @Override
    int add(FaceNotice faceNotice);

    @Override
    int remove(FaceNotice faceNotice);

    @Override
    int update(FaceNotice faceNotice);

    @Override
    List<FaceNotice> find(Map map);
}
