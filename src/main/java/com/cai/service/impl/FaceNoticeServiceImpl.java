package com.cai.service.impl;

import com.cai.dao.FaceNoticeDao;
import com.cai.domain.*;
import com.cai.service.*;
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
 * 面试通知的业务处理
 */
@Service
public class FaceNoticeServiceImpl implements FaceNoticeService {
    @Resource
    private FaceNoticeDao faceNoticeDao;

    @Resource
    private PostInfoService postInfoService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private HireInfoService hireInfoService;

    @Resource
    private TrainingService trainingService;

    @Resource
    private TrainingInfoService trainingInfoService;

    @Override
    public boolean add(FaceNotice faceNotice) {
        return faceNoticeDao.add(faceNotice) > 0;
    }

    @Override
    public boolean remove(FaceNotice faceNotice) {
        return faceNoticeDao.remove(faceNotice) > 0;
    }

    @Override
    public boolean update(FaceNotice faceNotice) {
        return faceNoticeDao.update(faceNotice) > 0;
    }

    @Override
    public List<FaceNotice> findAll() {
        return faceNoticeDao.find(null);
    }

    @Override
    public List<FaceNotice> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<FaceNotice> list = faceNoticeDao.find(map);
        return list;
    }

    @Override
    public List<FaceNotice> findByMap(Map<String, Object> map) {
        return faceNoticeDao.find(map);
    }

    /**
     * 创建面试通知
     *
     * @param faceNotice 新建的面试通知
     * @return 新建情况
     */
    @Override
    public Map<String, Object> addByCreate(FaceNotice faceNotice) throws ParseException {
        Map map = new HashedMap();
        //面试日期必须大于当前日期
        String nowForYMD = TimeUtil.nowForYMD();
        if (TimeUtil.startEndCompare(nowForYMD, faceNotice.getFaceTime()) < 1) {
            map.put("fail", "面试时间必须是今天以后!");
            return map;
        }
        //面试员的其他面试时间冲突
        int eid = faceNotice.getEmployee().getId();
        List<FaceNotice> notices = findByIf("eid", null, eid);
        if (notices.size() > 0) {
            for (FaceNotice notice : notices) {
                if (TimeUtil.startEndCompare(notice.getFaceTime(), faceNotice.getFaceTime()) == 0) {
                    map.put("fail", "与该面试员的其他面试时间有冲突!");
                    return map;
                }
            }
        }
        //面试员参加的培训时间冲突
        List<Training> trainings = trainingService.findByIf("eid", null, eid);
        for (Training training : trainings) {
            if (TimeUtil.midCompare(training.getTrainingInfo().getStartDate(),
                    faceNotice.getFaceTime(), training.getTrainingInfo().getEndDate())) {
                map.put("fail", "与该面试员参加培训的时间有冲突!");
                return map;
            }
        }
        //当培训老师的培训时间冲突
        List<TrainingInfo> trainingInfos = trainingInfoService.findByIf("eid", null, eid);
        for (TrainingInfo trainingInfo : trainingInfos) {
            if (TimeUtil.midCompare(trainingInfo.getStartDate(),
                    faceNotice.getFaceTime(), trainingInfo.getEndDate())) {
                map.put("fail", "与该面试员培训员工的时间有冲突!");
                return map;
            }
        }
        faceNotice.setStatus("通知中");
        add(faceNotice);
        map.put("success", "通知成功!");
        return map;
    }

    /**
     * 用户查找自己的面试通知(status条件)
     * OK
     *
     * @param user   用户
     * @param status 状态
     * @return 找到的面试通知集合
     */
    @Override
    public List<FaceNotice> findByUser(User user, String status) {
        List<FaceNotice> list = new ArrayList<>();
        //user没有简历则返回空集合
        if (user.getResume() == null) {
            return list;
        }
        //得到简历id
        int rid = user.getResume().getId();
        //通过简历id获得投递信息
        List<PostInfo> postInfos = postInfoService.findByIf("rid", null, rid);
        //没有投递信息则返回空集合
        if (postInfos.size() == 0) {
            return list;
        }
        //通过投递信息集合获得相关的面试通知集合
        Map<String, Object> map = new HashedMap();
        for (PostInfo postInfo : postInfos) {
            map.clear();
            map.put("postid", postInfo.getId());
            map.put("status", status);
            List<FaceNotice> faceNotices = faceNoticeDao.find(map);
            if (faceNotices.size() > 0) {
                list.add(faceNotices.get(0));
            }
        }
        return list;
    }

    /**
     * 得到详细的面试通知
     * OK
     *
     * @param faceNotice 面试通知
     * @return 详细的面试通知
     */
    @Override
    public FaceNotice findDetail(FaceNotice faceNotice) {
        int eid = faceNotice.getEmployee().getId();
        Employee employee = employeeService.findByIf("id", null, eid).get(0);
        int postid = faceNotice.getPostInfo().getId();
        PostInfo postInfo = postInfoService.findByIf("id", null, postid).get(0);
        HireInfo hireInfo = hireInfoService.findByIf("id", null, postInfo.getHireInfo().getId()).get(0);
        postInfo.setHireInfo(hireInfo);
        faceNotice.setEmployee(employee);
        faceNotice.setPostInfo(postInfo);
        return faceNotice;
    }


}
