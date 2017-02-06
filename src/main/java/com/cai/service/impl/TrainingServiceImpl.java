package com.cai.service.impl;

import com.cai.dao.TrainingDao;
import com.cai.dao.TrainingInfoDao;
import com.cai.domain.Training;
import com.cai.domain.TrainingInfo;
import com.cai.service.TrainingService;
import com.cai.utils.TimeUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 * <p>
 * 培训员工关联的业务处理接口实现
 */
@Service
public class TrainingServiceImpl implements TrainingService {
    @Resource
    private TrainingDao trainingDao;

    @Resource
    private TrainingInfoDao trainingInfoDaot;

    @Override
    public boolean add(Training training) {
        return trainingDao.add(training) > 0;
    }

    @Override
    public boolean remove(Training training) {
        return trainingDao.remove(training) > 0;
    }

    @Override
    public boolean update(Training training) {
        return trainingDao.update(training) > 0;
    }

    @Override
    public List<Training> findAll() {
        return trainingDao.find(null);
    }

    @Override
    public List<Training> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return trainingDao.find(map);
    }

    /**
     * 员工查找自己相关的'已发布'的培训信息
     *
     * @param eid 员工id
     * @return 培训信息的集合 size为0说明没有
     */
    @Override
    public List<TrainingInfo> findAllByEid(int eid) {
        List<TrainingInfo> trainingInfos = new ArrayList<>();
        Map map = new HashedMap();
        map.put("eid", eid);
        for (Training training : trainingDao.find(map)) {
            if ("已发布".equals(training.getTrainingInfo().getStatus())) {
                int tiid = training.getTrainingInfo().getId();
                map.clear();
                map.put("id", tiid);
                trainingInfos.add(trainingInfoDaot.find(map).get(0));
            }
        }
        return trainingInfos;
    }

    /**
     * 员工查找自己相关的已发布的培训通知 ps,培训详情的开始日期要大于当前日期
     *
     * @param eid 员工id
     * @return 培训信息的集合 size为0说明没有
     */
    @Override
    public List<TrainingInfo> findNowByEid(int eid) throws ParseException {
        List<TrainingInfo> trainingInfos = findAllByEid(eid);
        List<TrainingInfo> trainingInfoList = new ArrayList<>();
        for (TrainingInfo ti : trainingInfos) {
            if (TimeUtil.startEndCompare(TimeUtil.nowForYMD(), ti.getStartDate()) > 0) {
                trainingInfoList.add(ti);
            }
        }
        return trainingInfoList;
    }


}
