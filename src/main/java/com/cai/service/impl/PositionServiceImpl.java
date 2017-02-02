package com.cai.service.impl;

import com.cai.dao.DepartmentDao;
import com.cai.dao.PositionDao;
import com.cai.domain.Department;
import com.cai.domain.Position;
import com.cai.service.PositionService;
import com.cai.utils.TimeUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionDao positionDao;

    @Resource
    private DepartmentDao departmentDao;

    //添加职位 所选部门下职位名唯一
    @Override
    public boolean add(Position position) {
        int did = position.getDepartment().getId();
        Map map = new HashedMap();
        map.put("id", did);
        List<Department> list = departmentDao.find(map);
        if (list.size() > 0) {
            for (Department department : list) {
                for (Position posi : department.getPositionList()) {
                    if (posi.getpName().equals(position.getpName())) {
                        return false;
                    }
                }
            }
        }
        position.setCreateTime(TimeUtil.nowForYMD());
        return positionDao.add(position) > 0;
    }

    @Override
    public boolean remove(Position position) {
        return positionDao.remove(position) > 0;
    }

    //修改职位 所修改的职位与所属部门下的职位不能重复
    @Override
    public boolean update(Position position) {
        int did = position.getDepartment().getId();
        String pName = position.getpName();
        List<Position> positions = findByIf("pName", pName, 0);
        if (positions.size() > 0) {
            for (Position po : positions) {
                if (po.getDepartment().getId()==did) {
                    return false;
                }
            }
        }
        return positionDao.update(position) > 0;
    }

    @Override
    public List<Position> findAll() {
        return positionDao.find(null);
    }

    @Override
    public List<Position> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<Position> list = positionDao.find(map);
        return list;
    }
}
