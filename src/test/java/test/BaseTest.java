package test;

import com.cai.dao.UserDao;
import com.cai.domain.User;
import com.cai.utils.MatchUtil;
import com.cai.utils.MoneyUtil;
import com.cai.utils.StringUtil;
import com.cai.utils.TimeUtil;
import com.mysql.jdbc.StringUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by caibaolong on 2017/1/12.
 * 基本测试
 */
public class BaseTest {
    @Test
    public void test1() throws ParseException {
        System.out.println(MoneyUtil.saveTwoNumber(0));
        System.out.println(MoneyUtil.saveTwoNumber(0));

    }

    @Test
    public void test2() {
        String faceTime = "2017-01-31 10:50";
        try {
            faceTime = TimeUtil.toFormat(faceTime);
            String nowTime = TimeUtil.nowForYMDHMS();
            if (TimeUtil.timeCompare(faceTime, nowTime) == 1 && TimeUtil.timeBalance(nowTime, faceTime) < 2) {
                System.out.println("ok");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() throws ParseException {
        String s1 = "2017-01-12";//info.getStartDate();
        String s2 = "2017-01-27";//info.getEndDate()
        String s3 = "2017-01-28";//trainingInfo.getStartDate()
        String s4 = "2017-01-28";//trainingInfo.getEndDate()

        System.out.println(TimeUtil.midCompare(s1, s3, s2));
        System.out.println(TimeUtil.midCompare(s1, s4, s2));

        // if (TimeUtil.startEndCompare(s4, s1)<0)

    }

    @Test
    public void test4() throws ParseException {
        String s1 = "2017-01-12T22:12";
        s1 = TimeUtil.toFormat(s1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s1);
        System.out.println(date);
    }

    @Test//2017-01-12T12:12 --> 2017-01-12 09:00:00
    public void test5() throws ParseException {
        String s1 = "2017-01-12T09:00";
        String s2 = TimeUtil.toFormat(s1);
        String s3 = TimeUtil.getWorkInTime(s1);
        System.out.println(TimeUtil.timeCompare(s2, s3));

    }

    @Test//算出小时
    public void test6() throws ParseException {
        String s1 = "2017-01-12T10:30";
        String s2 = TimeUtil.toFormat(s1);
        String s3 = TimeUtil.getWorkInTime(s1);
        if (TimeUtil.timeCompare(s2, s3) < 0) {
            System.out.println("相差了" + TimeUtil.timeBalance(s2, s3) + "小时");

        }

    }

    @Test//保留小数
    public void test7() throws ParseException {
        double d = 900.112320D;

        System.out.println(MoneyUtil.saveTwoNumber(d));

    }

    @Test//保留小数
    public void test8() throws ParseException {
        System.out.println(StringUtil.toOne("12", "asd"));
        System.out.println(new StringBuffer("正常到").append("正常退").toString());


    }

    @Test//邮箱手机测试
    public void test9() {
        System.out.println(MatchUtil.isMail("cai@126.com"));
        System.out.println(MatchUtil.isPhone("13817135910"));

    }
}
