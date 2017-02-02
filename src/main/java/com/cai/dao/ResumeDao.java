package com.cai.dao;

import com.cai.domain.Resume;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/13.
 * 简历数据库操作接口
 */
@Repository
public interface ResumeDao extends BaseDao<Resume> {
    @Override
    int add(Resume resume);

    @Override
    int remove(Resume resume);

    @Override
    int update(Resume resume);

    @Override
    List<Resume> find(Map map);

    /**
     * 通过用户id查找
     * @param resume 带有user属性的简历
     * @return 满足条件的简历
     */
    Resume findByUserID(Resume resume);
}
