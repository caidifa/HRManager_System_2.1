package com.cai.dao;

import com.cai.domain.BonusPenalty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/19.
 * <p>
 * 对奖惩表的数据库操作
 */
@Repository
public interface BonusPenaltyDao extends BaseDao<BonusPenalty> {
    @Override
    int add(BonusPenalty bonusPenalty);

    @Override
    int remove(BonusPenalty bonusPenalty);

    @Override
    int update(BonusPenalty bonusPenalty);

    @Override
    List<BonusPenalty> find(Map map);
}
