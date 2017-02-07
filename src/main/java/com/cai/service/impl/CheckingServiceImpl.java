package com.cai.service.impl;

import com.cai.dao.CheckingDao;
import com.cai.domain.BonusPenalty;
import com.cai.domain.Checking;
import com.cai.domain.Employee;
import com.cai.service.BonusPenaltyService;
import com.cai.service.CheckingService;
import com.cai.service.EmployeeService;
import com.cai.utils.StringUtil;
import com.cai.utils.TimeUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/15.
 * <p>
 * 考勤打卡的业务接口实现
 */
@Service
public class CheckingServiceImpl implements CheckingService {
    @Resource
    private CheckingDao checkingDao;

    @Resource
    private BonusPenaltyService bonusPenaltyService;

    @Resource
    private EmployeeService employeeService;

    @Override
    public boolean add(Checking checking) {
        return checkingDao.add(checking) > 0;
    }

    @Override
    public boolean remove(Checking checking) {
        return checkingDao.remove(checking) > 0;
    }

    @Override
    public boolean update(Checking checking) {
        return checkingDao.update(checking) > 0;
    }

    @Override
    public List<Checking> findAll() {
        return checkingDao.find(null);
    }

    @Override
    public List<Checking> findByIf(String ifName, String content, int id) {
        Map<String, Object> map = new HashedMap();
        map.put(ifName, id != 0 ? id : content);
        return checkingDao.find(map);
    }

    //<editor-fold desc="上下班打卡处理">
    /**
     * 判断上班打卡时间的情况
     *
     * @param inTime 打卡时间
     * @return 情况(正常到, 迟到了[几小时])
     * @throws ParseException 时间转化异常
     */
    @Override
    public Map<String, Object> isLate(String inTime) throws ParseException {
        Map map = new HashedMap();
        //获得当日上班时间 自定义为上午09:00:00
        String workInTime = TimeUtil.getWorkInTime(inTime);
        //判断是否迟到
        if (TimeUtil.timeCompare(inTime, workInTime) < 0) {
            // 算出迟到几个小时
            map.put("late", TimeUtil.timeBalance(inTime, workInTime));
            return map;
        }
        map.put("ok", "正常到");
        return map;
    }

    /**
     * 进行上班打卡数据记录
     *
     * @param eid    上班打卡的员工id
     * @param inTime 上班打卡的时间
     * @return map key: ok正常到 否则是迟到 , 迟到则返回迟到小时数
     * @throws ParseException 时间转化异常
     */
    @Override
    public Map<String, Object> addInTime(int eid, String inTime) throws ParseException {
        //判断打卡时间 1 迟到了 2 正常到, 根据情况添加相应的奖惩
        Map<String, Object> map = new HashedMap();
        String checkDate = inTime.substring(0, 10);
        map.put("eid", eid);
        map.put("checkDate", checkDate);
        if (checkingDao.find(map).size() > 0) {
            map.put("fail", "你已在" + checkDate + "打过上班卡了");
            return map;
        }
        Employee emp = new Employee();
        emp.setId(eid);
        Checking checking = new Checking();
        checking.setEmployee(emp);
        checking.setCheckDate(checkDate);
        checking.setInTime(inTime);
        //判断是否迟到
        map = isLate(inTime);
        if (map.containsKey("ok")) {
            checking.setResult("正常到");
            map.clear();
            map.put("ok", "正常到");
        } else {
            checking.setResult("迟到了");
            //产生惩罚
            BonusPenalty b = new BonusPenalty();
            b.setEmployee(emp);
            b.setTime(checkDate);
            b.setType("惩罚");
            int i = (int) map.get("late");
            if (i > 3) {
                //旷工惩罚
                b.setReason("旷工了");
                //设置惩罚金额为日工资
                b.setMoney(employeeService.getDaySalary(eid));
            } else {
                //迟到惩罚
                b.setReason("迟到了" + i + "小时");
                //设置惩罚金额为日工资
                b.setMoney(employeeService.getHourSalary(eid) * 2 * i);
            }
            bonusPenaltyService.add(b);
        }
        add(checking);
        return map;
    }

    /**
     * 判断下班打卡时间的情况
     *
     * @param outTime 打卡时间
     * @return 情况(正常退, 早退了, 加班了)
     * @throws ParseException 时间转化异常
     */
    @Override
    public Map<String, Object> isEarly(String outTime) throws ParseException {
        Map map = new HashedMap();
        //获得当日上班时间 自定为下午18:00:00
        String workOutTime = TimeUtil.getWorkOutTime(outTime);
        //判断是否早退
        if (TimeUtil.timeCompare(outTime, workOutTime) > 0) {
            //早退了几小时
            map.put("early", TimeUtil.timeBalance(workOutTime, outTime));
            return map;
        } else {
            //判断是否加班了几个小时
            if (TimeUtil.timeBalance(outTime, workOutTime) > 1) {
                map.put("more", (TimeUtil.timeBalance(outTime, workOutTime) - 1));
                return map;
            }
        }
        map.put("ok", "正常退");
        return map;
    }

    /**
     * 进行下班打卡数据记录
     *
     * @param eid     下班打卡的员工id
     * @param outTime 下班打卡的时间
     * @return map key: ok正常退 否则是早退
     * @throws ParseException 时间转化异常
     */
    @Override
    public Map<String, Object> addOutTime(int eid, String outTime) throws ParseException {
        //判断下班打卡时间 1 早退了 2 正常退 3 加班了 ,根据情况添加相应的奖惩
        Map<String, Object> map = new HashedMap();
        String checkDate = outTime.substring(0, 10);
        map.put("eid", eid);
        map.put("checkDate", checkDate);
        List<Checking> list = checkingDao.find(map);
        if (list.size() == 0) {
            map.put("fail", "打卡失败!你还没在" + checkDate + "打过上班卡");
            return map;
        }
        Checking ck = list.get(0);
        System.out.println(ck);
        if (ck.getOutTime() != null) {
            map.put("fail", "打卡失败!你已经在" + checkDate + "打过下班卡");
            return map;
        }
        ck.setOutTime(outTime);
        //判断是否正常退
        map = isEarly(outTime);
        if (map.containsKey("ok")) {
            ck.setResult(StringUtil.toOne(ck.getResult(), "正常退"));
            map.clear();
            map.put("ok", "打卡成功!路上小心!");
        } else {
            BonusPenalty b = new BonusPenalty();
            b.setEmployee(ck.getEmployee());
            b.setTime(checkDate);

            //早退了
            if (map.containsKey("early")) {
                b.setType("惩罚");
                ck.setResult(StringUtil.toOne(ck.getResult(), "早退了"));
                int i = (int) map.get("early");
                b.setReason("早退了" + i + "小时");
                b.setMoney(employeeService.getHourSalary(eid) * 2 * i);
                map.clear();
                map.put("ok", "打卡成功!早退了" + i + "小时!");
            }

            //加班了
            if (map.containsKey("more")) {
                b.setType("奖励");
                ck.setResult(StringUtil.toOne(ck.getResult(), "加班了"));
                int i = (int) map.get("more");
                b.setReason("加班了" + i + "小时!");
                b.setMoney(employeeService.getHourSalary(eid) * 3 * i);
                map.clear();
                map.put("ok", "打卡成功!加班了" + i + "小时!");
            }
            bonusPenaltyService.add(b);
        }
        update(ck);
        return map;
    }
    //</editor-fold>

    /**
     * 得到指定员工指定年月的未工作天数
     *
     * @param eid    员工id
     * @param yMonth 年月
     * @return 未工作天数
     */
    @Override
    public int getDaysByEid(int eid, String yMonth) {
        Map map = new HashedMap();
        map.put("eid", eid);
        map.put("checkDate", yMonth + "%");
        return (22 - checkingDao.find(map).size());
    }
}
