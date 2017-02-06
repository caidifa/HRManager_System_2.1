package com.cai.service;

import com.cai.domain.Department;
import com.cai.domain.Position;

import java.util.List;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 简历的业务基本接口
 */
public interface PositionService extends BaseService<Position> {
    @Override
    boolean add(Position position);

    @Override
    boolean remove(Position position);

    @Override
    boolean update(Position position);

    @Override
    List<Position> findAll();

    @Override
    List<Position> findByIf(String ifName, String content, int id);
}
