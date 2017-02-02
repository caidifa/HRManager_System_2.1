package com.cai.dao;

import com.cai.domain.Bonuspenalty;
import com.cai.domain.Checking;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/19.
 * 对奖惩表的数据库操作
 */
@Repository
public interface BonuspenaltyDao extends BaseDao<Bonuspenalty> {
    @Override
    int add(Bonuspenalty bonuspenalty);

    @Override
    int remove(Bonuspenalty bonuspenalty);

    @Override
    int update(Bonuspenalty bonuspenalty);

    @Override
    List<Bonuspenalty> find(Map map);
}
