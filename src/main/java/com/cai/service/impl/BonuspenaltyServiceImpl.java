package com.cai.service.impl;

import com.cai.dao.BonuspenaltyDao;
import com.cai.domain.Bonuspenalty;
import com.cai.service.BonuspenaltyService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 */
@Service
public class BonuspenaltyServiceImpl implements BonuspenaltyService {
    @Resource
    private BonuspenaltyDao bonuspenaltyDao;

    @Override
    public boolean add(Bonuspenalty bonuspenalty) {
        return bonuspenaltyDao.add(bonuspenalty) > 0;
    }

    @Override
    public boolean remove(Bonuspenalty bonuspenalty) {
        return bonuspenaltyDao.remove(bonuspenalty) > 0;
    }

    @Override
    public boolean update(Bonuspenalty bonuspenalty) {
        return bonuspenaltyDao.update(bonuspenalty) > 0;
    }

    @Override
    public List<Bonuspenalty> findAll() {
        return bonuspenaltyDao.find(null);
    }

    @Override
    public List<Bonuspenalty> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<Bonuspenalty> list = bonuspenaltyDao.find(map);
        return list;
    }

    @Override
    public List<Bonuspenalty> findByMap(Map map) {
        return bonuspenaltyDao.find(map);
    }
}
