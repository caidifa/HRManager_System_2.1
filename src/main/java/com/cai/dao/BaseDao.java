package com.cai.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 接口编程基本接口
 *
 * @param <T> 泛型用于解耦,同时避免写重复代码
 */
public interface BaseDao<T> {
    /**
     * 添加某个对象
     *
     * @param t 待添加的对象
     * @return 返回受影响的行数
     */
    int add(T t);

    /**
     * 删除某个对象
     *
     * @param t 待删除的对象
     * @return 返回受影响的条数
     */
    int remove(T t);

    /**
     * 更新某个对象
     *
     * @param t 待更新对象
     * @return 返回受影响的条数
     */
    int update(T t);

    /**
     * 通过map查找对象 带有内部对象
     *
     * @param map 存放查询的对象的条件,为null时为查询所有
     * @return 返回该对应的对象集合
     */
    List<T> find(Map map);

}
