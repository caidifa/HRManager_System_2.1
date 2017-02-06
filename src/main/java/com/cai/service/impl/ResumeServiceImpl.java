package com.cai.service.impl;

import com.cai.dao.ResumeDao;
import com.cai.dao.UserDao;
import com.cai.domain.Resume;
import com.cai.service.ResumeService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 简历业务处理接口实现
 */
@Service
public class ResumeServiceImpl implements ResumeService {
    @Resource
    private ResumeDao resumeDao;

    public boolean add(Resume resume) {
        return resumeDao.add(resume) > 0;
    }

    public boolean remove(Resume resume) {
        return resumeDao.remove(resume) > 0;
    }

    public boolean update(Resume resume) {
        return resumeDao.update(resume) > 0;
    }

    public List<Resume> findAll() {
        return resumeDao.find(null);
    }

    public List<Resume> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return resumeDao.find(map);
    }

    /**
     * 用户创建简历
     * 不允许用户填写的手机号,邮箱地址,身份证号有相同的
     *
     * @param resume 需要添加的简历
     * @return 添加情况回馈success/fail:message
     */
    public Map<String, Object> addByCreate(Resume resume) {
        Map<String, Object> map = new HashedMap();
        //身份证号重复
        if (findByIf("idCard", resume.getIdCard(), 0).size() > 0) {
            map.put("fail", "该身份证已存在!");
            return map;
        }
        add(resume);
        map.put("success", "添加简历成功!");
        return map;
    }

    /**
     * 修改简历
     *
     * @param resume 用户修改好的简历
     * @return 修改情况回馈
     */
    @Override
    public Map<String, Object> updateByEdit(Resume resume) {
        Map<String, Object> map = new HashedMap();
        //真实姓名重复
        List<Resume> resumes = findByIf("realName", resume.getRealName(), 0);
        if (resumes.size() > 0 && resumes.get(0).getId() != resume.getId()) {
            map.put("fail", "该真实姓名已存在!");
            return map;
        }
        //身份证号重复
        resumes = findByIf("idCard", resume.getIdCard(), 0);
        if (resumes.size() > 0 && resumes.get(0).getId() != resume.getId()) {
            map.put("fail", "该身份证已存在!");
            return map;
        }
        update(resume);
        map.put("success", "修改简历成功!");
        return map;
    }


}
