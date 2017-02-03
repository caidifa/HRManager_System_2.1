package test;

import com.cai.dao.SalaryDao;
import com.cai.domain.Salary;
import com.cai.service.SalaryService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/20.
 */
public class SalaryTest {
    @Test
    public void test1() {
        SalaryService salaryService = new ClassPathXmlApplicationContext("beans.xml").getBean(SalaryService.class);
        Map map = new HashMap();
        map.put("eid", 4);

        List<Salary> list = salaryService.findAll();

        for (Salary salary : list) {
            System.out.println(salary);
        }

        System.out.println(list.size());

    }
}
