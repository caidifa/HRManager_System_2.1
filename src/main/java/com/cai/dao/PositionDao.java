package com.cai.dao;

import com.cai.domain.Position;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 职位数据库操作接口
 */
@Repository
public interface PositionDao extends BaseDao<Position> {
    @Override
    int add(Position position);

    @Override
    int remove(Position position);

    @Override
    int update(Position position);

    @Override
    List<Position> find(Map map);

    /**
     * 通过部门id查找
     * @param position 带有部门的职位
     * @return 满足条件的职位
     */
    List<Position> findByDepID(Position position);
}
