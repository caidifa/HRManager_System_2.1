package com.cai.dao;

import com.cai.domain.PostInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 投递信息数据库操作接口
 */
@Repository
public interface PostInfoDao extends BaseDao<PostInfo> {
    @Override
    int add(PostInfo postInfo);

    @Override
    int remove(PostInfo postInfo);

    @Override
    int update(PostInfo postInfo);

    @Override
    List<PostInfo> find(Map map);

}
