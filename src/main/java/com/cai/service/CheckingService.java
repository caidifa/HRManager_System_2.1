package com.cai.service;

import com.cai.domain.Checking;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * <p>
 * 考勤的业务基本接口
 */
public interface CheckingService extends BaseService<Checking> {
    @Override
    boolean add(Checking checking);

    @Override
    boolean remove(Checking checking);

    @Override
    boolean update(Checking checking);

    @Override
    List<Checking> findAll();

    @Override
    List<Checking> findByIf(String ifName, String content, int id);

    /**
     * 判断上班打卡时间的情况
     *
     * @param inTime 上班打卡时间
     * @return 情况(正常到, 迟到了[几小时], 旷工了)
     * @throws ParseException 时间日期与String转化异常
     */
    Map<String, Object> isLate(String inTime) throws ParseException;

    /**
     * 进行上班打卡数据记录
     *
     * @param eid    上班打卡的员工id
     * @param inTime 上班打卡的时间
     * @return map key: ok正常到 否则是迟到 , 迟到则返回迟到小时数
     * @throws ParseException 时间日期与String转化异常
     */
    Map<String, Object> addInTime(int eid, String inTime) throws ParseException;


    /**
     * 判断下班打卡时间的情况
     *
     * @param outTime 下班打卡时间
     * @return 情况(正常退, 早退了, 加班了)
     * @throws ParseException 时间日期与String转化异常
     */
    Map<String, Object> isEarly(String outTime) throws ParseException;

    /**
     * 进行下班打卡数据记录
     *
     * @param eid     下班打卡的员工id
     * @param outTime 下班打卡的时间
     * @return map key: ok正常退 否则是早退
     * @throws ParseException 时间日期与String转化异常
     */
    Map<String, Object> addOutTime(int eid, String outTime) throws ParseException;

    /**
     * 得到指定员工指定年月的未工作天数
     *
     * @param eid    员工id
     * @param yMonth 年月
     * @return 未工作天数
     */
    int getDaysByEid(int eid, String yMonth);

}
