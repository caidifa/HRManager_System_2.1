package com.cai.service;

import com.cai.domain.HireInfo;
import com.cai.domain.Resume;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 简历的业务基本接口
 */
public interface HireInfoService extends BaseService<HireInfo> {

    @Override
    boolean add(HireInfo hireInfo);

    @Override
    boolean remove(HireInfo hireInfo);

    @Override
    boolean update(HireInfo hireInfo);

    @Override
    List<HireInfo> findAll();

    @Override
    List<HireInfo> findByIf(String ifName, String content, int id);

    List<HireInfo> findByMap(Map<String, Object> map);

    /**
     * 管理员创建新的招聘信息
     *
     * @param hireInfo 招聘信息
     * @return 添加情况
     */
    Map<String,Object> addByCreate(HireInfo hireInfo);

}
