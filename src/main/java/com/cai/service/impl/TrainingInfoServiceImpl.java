package com.cai.service.impl;

import com.cai.dao.TrainingInfoDao;
import com.cai.domain.FaceNotice;
import com.cai.domain.Training;
import com.cai.domain.TrainingInfo;
import com.cai.service.FaceNoticeService;
import com.cai.service.TrainingInfoService;
import com.cai.utils.TimeUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 * 培训信息的业务处理接口实现
 */
@Service
public class TrainingInfoServiceImpl implements TrainingInfoService {
    @Resource
    private TrainingInfoDao trainingInfoDao;

    @Resource
    private FaceNoticeService faceNoticeService;

    @Override
    public boolean add(TrainingInfo training) {
        return trainingInfoDao.add(training) > 0;
    }

    @Override
    public boolean remove(TrainingInfo training) {
        return trainingInfoDao.remove(training) > 0;
    }

    @Override
    public boolean update(TrainingInfo training) {
        return trainingInfoDao.update(training) > 0;
    }

    @Override
    public List<TrainingInfo> findAll() {
        return trainingInfoDao.find(null);
    }

    @Override
    public List<TrainingInfo> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<TrainingInfo> list = trainingInfoDao.find(map);
        return list;
    }

    /**
     * 管理员添加培训
     *
     * @param trainingInfo 需要添加的培训信息
     * @return 添加时的情况
     */
    @Override
    public Map<String, Object> addTrainAdmin(TrainingInfo trainingInfo) throws ParseException {
        //同一个老师的多个培训里(已发布)的开始至结束日期不能重叠
        int eid = trainingInfo.getTeacher().getId();
        Map map = new HashedMap();
        map.put("eid", eid);
        map.put("status", "已发布");
        List<TrainingInfo> infos = trainingInfoDao.find(map);
        String s3 = trainingInfo.getStartDate();
        String s4 = trainingInfo.getEndDate();
        for (TrainingInfo info : infos) {
            String s1 = info.getStartDate();
            String s2 = info.getEndDate();
            if (TimeUtil.midCompare(s1, s3, s2) || TimeUtil.midCompare(s1, s4, s2)) {
                map.clear();
                map.put("fail", "与其他培训时间有冲突!");
                return map;
            }
        }
        //不能和老师的面试时间冲突
        List<FaceNotice> notices = faceNoticeService.findByIf("eid", null, eid);
        if (notices.size() > 0) {
            for (FaceNotice notice : notices) {
                if (TimeUtil.midCompare(s3, notice.getFaceTime(), s4)) {
                    map.clear();
                    map.put("fail", "与面试时间有冲突!");
                    return map;
                }
            }
        }
        trainingInfo.setStatus("未发布");
        add(trainingInfo);
        map.put("success", "成功!");
        return map;
    }

    /**
     * 管理员发布一条培训
     *
     * @param trainingInfo 需要发布的培训信息
     * @return 发布时的情况
     */
    @Override
    public Map<String, Object> updateByPostOne(TrainingInfo trainingInfo) throws ParseException {
        //同一个老师的多个培训里(已发布)的开始至结束日期不能重叠
        int eid = trainingInfo.getTeacher().getId();
        Map map = new HashedMap();
        map.put("eid", eid);
        map.put("status", "已发布");
        List<TrainingInfo> infos = trainingInfoDao.find(map);
        String s1 = trainingInfo.getStartDate();
        String s2 = trainingInfo.getEndDate();
        for (TrainingInfo info : infos) {
            String s4 = info.getEndDate();
            if (TimeUtil.midCompare(info.getStartDate(), s1, s4) || TimeUtil.midCompare(info.getStartDate(), s2, s4)) {
                map.clear();
                map.put("fail", "与该老师的其他培训时间有冲突!");
                return map;
            }
        }
        //不能和老师的面试时间冲突
        List<FaceNotice> notices = faceNoticeService.findByIf("eid", null, eid);
        if (notices.size() > 0) {
            for (FaceNotice notice : notices) {
                if (TimeUtil.midCompare(trainingInfo.getStartDate(), notice.getFaceTime(), s2)) {
                    map.clear();
                    map.put("fail", "与该老师的面试时间有冲突!");
                    return map;
                }
            }
        }
        trainingInfo.setStatus("已发布");
        update(trainingInfo);
        map.put("success", "成功!");
        return map;
    }

    @Override
    public Map<String, Object> updateByEdit(TrainingInfo trainingInfo) throws ParseException {
        //同一个老师的多个培训里(已发布)的开始至结束日期不能重叠
        int eid = trainingInfo.getTeacher().getId();
        Map map = new HashedMap();
        map.put("eid", eid);
        map.put("status", "已发布");
        List<TrainingInfo> infos = trainingInfoDao.find(map);
        String s3 = trainingInfo.getStartDate();
        String s4 = trainingInfo.getEndDate();
        for (TrainingInfo info : infos) {
            String s1 = info.getStartDate();
            String s2 = info.getEndDate();
            if (TimeUtil.midCompare(s1, s3, s2) || TimeUtil.midCompare(s1, s4, s2)) {
                map.clear();
                map.put("fail", "与其他培训时间有冲突!");
                return map;
            }
        }
        //不能和老师的面试时间冲突
        List<FaceNotice> notices = faceNoticeService.findByIf("eid", null, eid);
        if (notices.size() > 0) {
            for (FaceNotice notice : notices) {
                if (TimeUtil.midCompare(s3, notice.getFaceTime(), s4)) {
                    map.clear();
                    map.put("fail", "与面试时间有冲突!");
                    return map;
                }
            }
        }
        update(trainingInfo);
        map.put("success", "成功!");
        return map;
    }


}
