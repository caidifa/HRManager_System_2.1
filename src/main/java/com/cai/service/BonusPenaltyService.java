package com.cai.service;

import com.cai.domain.BonusPenalty;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 奖惩的业务基本接口
 */
public interface BonusPenaltyService extends BaseService<BonusPenalty> {
    @Override
    boolean add(BonusPenalty bonusPenalty);

    @Override
    boolean remove(BonusPenalty bonusPenalty);

    @Override
    boolean update(BonusPenalty bonusPenalty);

    @Override
    List<BonusPenalty> findAll();

    @Override
    List<BonusPenalty> findByIf(String ifName, String content, int id);

    /**
     * 多条件查询
     *
     * @param map 查询条件
     * @return 符合条件的对象集合
     */
    List<BonusPenalty> findByMap(Map map);
}
