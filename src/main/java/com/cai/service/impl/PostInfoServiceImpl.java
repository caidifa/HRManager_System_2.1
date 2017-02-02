package com.cai.service.impl;

import com.cai.dao.HireInfoDao;
import com.cai.dao.PostInfoDao;
import com.cai.domain.HireInfo;
import com.cai.domain.PostInfo;
import com.cai.domain.Resume;
import com.cai.service.PostInfoService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 * 投递信息的业务处理
 */
@Service
public class PostInfoServiceImpl implements PostInfoService {
    @Resource
    private PostInfoDao postInfoDao;
    @Resource
    private HireInfoDao hireInfoDao;

    @Override
    public boolean add(PostInfo postInfo) {
        return postInfoDao.add(postInfo) > 0;
    }

    @Override
    public boolean remove(PostInfo postInfo) {
        return postInfoDao.remove(postInfo) > 0;
    }

    @Override
    public boolean update(PostInfo postInfo) {
        return postInfoDao.update(postInfo) > 0;
    }

    @Override
    public List<PostInfo> findAll() {
        return postInfoDao.find(null);
    }

    @Override
    public List<PostInfo> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        if (id != 0) {
            map.put(ifName, id);
        } else {
            map.put(ifName, content);
        }
        List<PostInfo> list = postInfoDao.find(map);
        return list;
    }

    /**
     * 用户选择招聘信息投递简历→生成投递信息
     *
     * @param resume 用户简历
     * @param hireInfo 用户选择的招聘信息
     * @return 回馈投递情况
     */
    @Override
    public Map<String, Object> addByPostResume(Resume resume, HireInfo hireInfo) {
        Map map = new HashMap();
        map.put("rid", resume.getId());
        map.put("hid", hireInfo.getId());
        if (postInfoDao.find(map).size()>0) {
            map.clear();
            map.put("fail", "你已经投递过此项招聘!");
            return map;
        }
        PostInfo pi = new PostInfo();
        pi.setResume(resume);
        pi.setHireInfo(hireInfo);
        pi.setRemark("未读");
        add(pi);
        map.clear();
        map.put("success", "投递成功!");
        return map;
    }

    /**
     * 得到详细的投递信息
     * OK
     *
     * @param postInfo 投递信息
     * @return 详细的投递信息
     */
    @Override
    public PostInfo findDetail(PostInfo postInfo) {
        int hid = postInfo.getHireInfo().getId();
        Map map = new HashMap();
        map.put("id", hid);
        HireInfo hireInfo = hireInfoDao.find(map).get(0);
        postInfo.setHireInfo(hireInfo);
        return postInfo;
    }


}
