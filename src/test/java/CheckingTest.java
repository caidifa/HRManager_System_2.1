import com.cai.dao.BonuspenaltyDao;
import com.cai.dao.CheckingDao;
import com.cai.domain.Bonuspenalty;
import com.cai.domain.Checking;
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
        BonuspenaltyDao bd = new ClassPathXmlApplicationContext("beans.xml").getBean(BonuspenaltyDao.class);
        Map map = new HashMap();
        map.put("timeLike", "2017-02-10");
        List<Bonuspenalty> bonuspenaltyList = bd.find(map);
        for (Bonuspenalty bonuspenalty : bonuspenaltyList) {
            System.out.println(bonuspenalty);
        }
        System.out.println(bonuspenaltyList.size());

    }

    @Test
    public void test3() {
        CheckingDao cd = new ClassPathXmlApplicationContext("beans.xml").getBean(CheckingDao.class);


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
