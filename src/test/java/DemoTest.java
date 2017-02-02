import com.cai.dao.*;
import com.cai.domain.*;
import com.cai.service.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by caibaolong on 2017/1/13.
 */
public class DemoTest {

    // departments部门
    @Test
    public void test0() {
        DepartmentService ds = new ClassPathXmlApplicationContext("beans.xml").getBean(DepartmentService.class);
        List<Department> departments = ds.findAll();
        // List<Department> id = ds.findAllOnly();
        // System.out.println(id.size());
        for (Department department : departments) {
            System.out.println(department);
            System.out.println(department.getPositionList().size());
            System.out.println("------------------------------------------------------------------------");
        }
    }

    // positions职位
    @Test
    public void test1() {
        PositionDao positionDao = new ClassPathXmlApplicationContext("beans.xml").getBean(PositionDao.class);
        List<Position> positions = positionDao.find(null);
        for (Position position : positions) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println(position);
            System.out.println("------------------------------------------------------------------------");
        }
    }

    // employees员工
    @Test
    public void test2() {
        EmployeeService employeeService = new ClassPathXmlApplicationContext("beans.xml").getBean(EmployeeService.class);
        // Map map = new HashMap();
        // map.put("level", "0");
        // List<Employee> employees = employeeService.findAll();
        // for (Employee employee : employees) {
        //     System.out.println("------------------------------------------------------------------------");
        //     System.out.println(employee);
        //     System.out.println("------------------------------------------------------------------------");
        // }
        // Employee employee = new Employee();
        // employee.setEmpNumber("6660203001");
        // employee.setPassword("22");
        // employee.setLevel("0");
        // System.out.println(employeeService.empLogin(employee));
        System.out.println(employeeService.getDaySalary(4));
        System.out.println(employeeService.getHourSalary(4));

    }

    // hireInfo招聘信息
    @Test
    public void test4() {
        HireInfoService hireInfoService = new ClassPathXmlApplicationContext("beans.xml").getBean(HireInfoService.class);

        List<HireInfo> id = hireInfoService.findByIf("id", null, 1);
        System.out.println(id);
    }

    // 投递信息
    @Test
    public void test5() {
        PostInfoService postInfoService = new ClassPathXmlApplicationContext("beans.xml").getBean(PostInfoService.class);
        List<PostInfo> all = postInfoService.findByIf("hid", null, 2);
        System.out.println(all.size());
        for (PostInfo postInfo : all) {
            System.out.println("------------------------------------------------------------------------");
            System.out.println(postInfo);
            System.out.println("------------------------------------------------------------------------");
        }


    }

    // 面试通知
    @Test
    public void test6() {
        FaceNoticeService fn = new ClassPathXmlApplicationContext("beans.xml").getBean(FaceNoticeService.class);
        List<FaceNotice> all = fn.findAll();
        System.out.println(all.size());

    }

    // 面试情况
    @Test
    public void test7() {
        FaceInfoService fs = new ClassPathXmlApplicationContext("beans.xml").getBean(FaceInfoService.class);
        // List<FaceInfo> all = fs.findAll();
        // System.out.println(all.size());

        List<FaceInfo> byIf = fs.findByIf("status", "已通知", 0);
        System.out.println(byIf);
    }

    // 测试 3
    @Test
    public void test8() {

    }

    // 测试 3
    @Test
    public void test9() {

    }

    // 测试 3
    @Test
    public void test10() {

    }

    // 测试 3
    @Test
    public void test11() {

    }
}
