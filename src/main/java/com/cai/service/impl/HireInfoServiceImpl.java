package com.cai.service.impl;

import com.cai.dao.HireInfoDao;
import com.cai.domain.HireInfo;
import com.cai.service.HireInfoService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 * <p>
 * 招聘信息的业务处理接口实现
 */
@Service
public class HireInfoServiceImpl implements HireInfoService {
    @Resource
    private HireInfoDao hireInfoDao;

    @Override
    public boolean add(HireInfo hireInfo) {
        return hireInfoDao.add(hireInfo) > 0;
    }

    @Override
    public boolean remove(HireInfo hireInfo) {
        return hireInfoDao.remove(hireInfo) > 0;
    }

    @Override
    public boolean update(HireInfo hireInfo) {
        return hireInfoDao.update(hireInfo) > 0;
    }

    @Override
    public List<HireInfo> findAll() {
        return hireInfoDao.find(null);
    }

    @Override
    public List<HireInfo> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return hireInfoDao.find(map);
    }

    @Override
    public List<HireInfo> findByMap(Map<String, Object> map) {
        return hireInfoDao.find(map);
    }

    /**
     * 管理员创建新的招聘信息
     *
     * @param hireInfo 招聘信息
     * @return 添加情况
     */
    @Override
    public Map<String, Object> addByCreate(HireInfo hireInfo) {
        Map<String, Object> map = new HashedMap();
        map.put("did", hireInfo.getDepartment().getId());
        map.put("pid", hireInfo.getPosition().getId());
        map.put("status", "招聘中");
        if (hireInfoDao.find(map).size() > 0) {
            map.clear();
            map.put("fail", "已经有此部门下此职位的招聘信息了!");
        } else {
            add(hireInfo);
            map.clear();
            map.put("success", "添加成功!");
        }
        return map;
    }
}
