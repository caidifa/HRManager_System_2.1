package test;

import com.cai.domain.Resume;
import com.cai.service.ResumeService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by caibaolong on 2017/1/13.
 */
public class ResumeTest {
    private ResumeService resumeService = new ClassPathXmlApplicationContext("beans.xml").getBean(ResumeService.class);

    // 基本方法进行测试
    @Test
    public void test1() {
        // Resume r = new Resume();
        // r.setReal_name("caibaolong");
        // r.setUser_id(1);
        // resumeService.addResume(r);
        List<Resume> all = resumeService.findAll();
        for (Resume resume : all) {
            System.out.println(resume);
        }

    }

    // 测试:
    @Test
    public void test2() {

    }

    // 测试
    @Test
    public void test3() {

    }

    // 测试
    @Test
    public void test4() {

    }
}
