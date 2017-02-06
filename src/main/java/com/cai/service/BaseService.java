package com.cai.service;

import java.util.List;

/**
 * Created by caibaolong on 2017/1/14.
 * <p>
 * 业务逻辑基本接口
 *
 * @param <T> 泛型用于解耦,同时避免写重复代码
 */
public interface BaseService<T> {
    /**
     * 添加某个对象
     *
     * @param t 待添加的对象
     * @return 成功是否
     */
    boolean add(T t);

    /**
     * 删除某个对象
     *
     * @param t 待删除的对象
     * @return 成功是否
     */
    boolean remove(T t);

    /**
     * 更新某个对象
     *
     * @param t 待更新对象
     * @return 成功是否
     */
    boolean update(T t);

    /**
     * 查找所有对象
     *
     * @return 所有对象集合
     */
    List<T> findAll();

    /**
     * 带条件查询对象
     *
     * @param ifName  条件名
     * @param content 条件内容
     * @param id      条件名为 *id,则用id查找
     * @return 查找结果 null:没有满足条件的对象
     */
    List<T> findByIf(String ifName, String content, int id);

}
