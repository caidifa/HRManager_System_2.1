package com.cai.service;

import com.cai.domain.Department;
import com.cai.domain.HireInfo;
import com.cai.domain.PostInfo;
import com.cai.domain.Resume;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 简历的业务基本接口
 */
public interface PostInfoService extends BaseService<PostInfo> {
    @Override
    boolean add(PostInfo postInfo);

    @Override
    boolean remove(PostInfo postInfo);

    @Override
    boolean update(PostInfo postInfo);

    @Override
    List<PostInfo> findAll();

    @Override
    List<PostInfo> findByIf(String ifName, String content, int id);

    /**
     * 用户选择招聘信息投递简历→生成投递信息
     *
     * @param resume   用户简历
     * @param hireInfo 用户选择的招聘信息
     * @return 回馈投递情况
     */
    Map<String, Object> addByPostResume(Resume resume, HireInfo hireInfo);

    /**
     * 得到详细的投递信息
     *
     * @param postInfo 投递信息
     * @return 详细的投递信息
     */
    PostInfo findDetail(PostInfo postInfo);

}
