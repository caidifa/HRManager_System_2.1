package com.cai.service.impl;

import com.cai.dao.BonusPenaltyDao;
import com.cai.domain.BonusPenalty;
import com.cai.service.BonusPenaltyService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 * <p>
 * 奖惩的业务基本接口实现
 */
@Service
public class BonusPenaltyServiceImpl implements BonusPenaltyService {
    @Resource
    private BonusPenaltyDao bonusPenaltyDao;

    @Override
    public boolean add(BonusPenalty bonusPenalty) {
        return bonusPenaltyDao.add(bonusPenalty) > 0;
    }

    @Override
    public boolean remove(BonusPenalty bonusPenalty) {
        return bonusPenaltyDao.remove(bonusPenalty) > 0;
    }

    @Override
    public boolean update(BonusPenalty bonusPenalty) {
        return bonusPenaltyDao.update(bonusPenalty) > 0;
    }

    @Override
    public List<BonusPenalty> findAll() {
        return bonusPenaltyDao.find(null);
    }

    @Override
    public List<BonusPenalty> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return bonusPenaltyDao.find(map);
    }

    @Override
    public List<BonusPenalty> findByMap(Map map) {
        return bonusPenaltyDao.find(map);
    }
}
