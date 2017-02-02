import com.cai.dao.TrainingDao;
import com.cai.dao.TrainingInfoDao;
import com.cai.domain.Training;
import com.cai.domain.TrainingInfo;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/17.
 * 培训测试
 */
public class TrainingTest {
    @Test
    public void test1() {
        TrainingDao td = new ClassPathXmlApplicationContext("beans.xml").getBean(TrainingDao.class);
        TrainingInfoDao tid = new ClassPathXmlApplicationContext("beans.xml").getBean(TrainingInfoDao.class);
        Map map = new HashMap<>();
        map.put("eid", 3);
        List<TrainingInfo> trainingInfos = tid.find(map);
        for (TrainingInfo trainingInfo : trainingInfos) {

            System.out.println(trainingInfo);
        }

    }

    @Test
    public void test2() {
        TrainingDao td = new ClassPathXmlApplicationContext("beans.xml").getBean(TrainingDao.class);
        Map map = new HashMap<>();
        map.put("eid", 4);
        List<Training> trainings = td.find(map);
        List<TrainingInfo> trainingInfoList = new ArrayList<>();
        for (Training training : trainings) {
            if ("已发布".equals(training.getTrainingInfo().getStatus())) {
                trainingInfoList.add(training.getTrainingInfo());
            }
        }
        for (TrainingInfo trainingInfo : trainingInfoList) {
            System.out.println(trainingInfo);
        }
    }

    @Test
    public void test3() {

    }

    @Test
    public void test4() {

    }
}
