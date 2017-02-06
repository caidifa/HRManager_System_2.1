package com.cai.service;

import com.cai.domain.Resume;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 简历的业务基本接口
 */
public interface ResumeService extends BaseService<Resume> {
    @Override
    boolean add(Resume resume);

    @Override
    boolean remove(Resume resume);

    @Override
    boolean update(Resume resume);

    @Override
    List<Resume> findAll();

    @Override
    List<Resume> findByIf(String ifName, String content, int id);

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
