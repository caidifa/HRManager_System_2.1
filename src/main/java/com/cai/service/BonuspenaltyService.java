package com.cai.service;

import com.cai.domain.Bonuspenalty;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 奖惩的业务基本接口
 */
public interface BonuspenaltyService extends BaseService<Bonuspenalty> {
    @Override
    boolean add(Bonuspenalty bonuspenalty);

    @Override
    boolean remove(Bonuspenalty bonuspenalty);

    @Override
    boolean update(Bonuspenalty bonuspenalty);

    @Override
    List<Bonuspenalty> findAll();

    @Override
    List<Bonuspenalty> findByIf(String ifName, String content, int id);

    List<Bonuspenalty> findByMap(Map map);
}
