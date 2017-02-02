package com.cai.service;

import com.cai.domain.Resume;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 简历的业务基本接口
 */
public interface ResumeService extends BaseService<Resume> {

    boolean add(Resume resume);

    boolean remove(Resume resume);

    boolean update(Resume resume);

    List<Resume> findAll();

    List<Resume> findByIf(String ifName, String content, int id);

    /**
     * 通过用户id查找
     *
     * @param resume 带有user属性的简历
     * @return 满足条件的简历
     */
    Resume findByUserID(Resume resume);

    /**
     * 创建简历
     *
     * @param resume 用户填好的简历
     * @return 添加情况回馈
     */
    Map<String, Object> addByCreate(Resume resume);

    /**
     * 修改简历
     *
     * @param resume 用户修改好的简历
     * @return 修改情况回馈
     */
    Map<String, Object> updateByEdit(Resume resume);



}
