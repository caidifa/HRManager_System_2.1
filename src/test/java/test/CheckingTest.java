package test;

import com.cai.dao.BonusPenaltyDao;
import com.cai.dao.CheckingDao;
import com.cai.domain.BonusPenalty;
import com.cai.domain.Checking;
import com.cai.service.BonusPenaltyService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/19.
 */
public class CheckingTest {
    @Test
    public void test1() {
        CheckingDao cd = new ClassPathXmlApplicationContext("beans.xml").getBean(CheckingDao.class);
        Map map = new HashMap();
        map.put("eid", 4);
        List<Checking> checkingList = cd.find(map);
        for (Checking checking : checkingList) {
            System.out.println(checking);
        }
        System.out.println(checkingList.size());

    }

    @Test
    public void test2() {
        BonusPenaltyDao bd = new ClassPathXmlApplicationContext("beans.xml").getBean(BonusPenaltyDao.class);
        Map map = new HashMap();
        map.put("timeLike", "2017-02-10");
        List<BonusPenalty> bonusPenaltyList = bd.find(map);
        for (BonusPenalty bonusPenalty : bonusPenaltyList) {
            System.out.println(bonusPenalty);
        }
        System.out.println(bonusPenaltyList.size());

    }

    @Test
    public void test3() {
        BonusPenaltyService bps = new ClassPathXmlApplicationContext("beans.xml").getBean(BonusPenaltyService.class);
        List<BonusPenalty> id = bps.findByIf("time", "2017-02-02", 0);
        System.out.println(id.size());


    }

    @Test
    public void test4() {
        CheckingDao cd = new ClassPathXmlApplicationContext("beans.xml").getBean(CheckingDao.class);


    }

    @Test
    public void test5() {
        CheckingDao cd = new ClassPathXmlApplicationContext("beans.xml").getBean(CheckingDao.class);


    }
    
}
