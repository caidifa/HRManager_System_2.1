package com.cai.service.impl;

import com.cai.dao.FaceInfoDao;
import com.cai.dao.FaceNoticeDao;
import com.cai.domain.FaceInfo;
import com.cai.domain.FaceNotice;
import com.cai.domain.User;
import com.cai.service.FaceInfoService;
import com.cai.service.FaceNoticeService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 */
@Service
public class FaceInfoServiceImpl implements FaceInfoService {
    @Resource
    private FaceInfoDao faceInfoDao;

    @Resource
    private FaceNoticeService faceNoticeService;

    @Override
    public boolean add(FaceInfo faceInfo) {
        return faceInfoDao.add(faceInfo) > 0;
    }

    @Override
    public boolean remove(FaceInfo faceInfo) {
        return faceInfoDao.remove(faceInfo) > 0;
    }

    @Override
    public boolean update(FaceInfo faceInfo) {
        return faceInfoDao.update(faceInfo) > 0;
    }

    @Override
    public List<FaceInfo> findAll() {
        return faceInfoDao.find(null);
    }

    @Override
    public List<FaceInfo> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<FaceInfo> list = faceInfoDao.find(map);
        return list;
    }

    /**
     * 用户查找自己的面试情况(有status条件)
     * OK
     *
     * @param user 用户
     * @param status 状态
     * @return 找到的面试情况集合
     */
    @Override
    public List<FaceInfo> findByUser(User user, String status) {
        List<FaceInfo> list = new ArrayList<>();
        //先找到该用户的所有"已面试"的面试通知
        List<FaceNotice> faceNotices = faceNoticeService.findByUser(user, "已面试");
        if (faceNotices.size() == 0) {
            return list;
        }
        //通过找到的面试通知得到该用户的面试情况(有status条件)
        Map<String, Object> map = new HashedMap();
        for (FaceNotice faceNotice : faceNotices) {
            map.clear();
            map.put("fnid", faceNotice.getId());
            map.put("status", status);
            List<FaceInfo> faceInfos = faceInfoDao.find(map);
            if (faceInfos.size()>0) {
                list.add(faceInfos.get(0));
            }
        }
        return list;
    }

}
