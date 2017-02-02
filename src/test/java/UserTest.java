import com.cai.domain.User;
import com.cai.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by caibaolong on 2017/1/12.
 * 基本测试
 */
public class UserTest {
    private UserService userService = new ClassPathXmlApplicationContext("beans.xml").getBean(UserService.class);

    // 基本方法进行测试
    @Test
    public void test1() {
        User user = new User();
        user.setEmail("cai@11.com");
        user.setPassword("11");
        // userService.addUser(user);
        List<User> id = userService.findByIf("phone", "11", 1);
        System.out.println(id.get(0).getResume());
        // List<User> all = userService.findAll();
        // for (User u : all) {
        //     System.out.println(u);
        // }
        // System.out.println(byIf);
    }

    // 登陆测试
    @Test
    public void test2() {
        User user = new User();
        user.setPhone("13817135910");
        user.setPassword("1111");
        Map<String, Object> map = userService.findByLogin(user);
        System.out.println(map);
    }

    // 注册测试
    @Test
    public void test3() {
        User user = new User();
        user.setEmail("caidifa@126.com");
        user.setPassword("222");
        Map<String, Object> map = userService.addByRegister(user);
        System.out.println(map);
    }

    // 登陆后修改测试
    @Test
    public void test4() {
        User user = new User();
        user.setId(1);
        // user.setEmail("caidifa@166.com");
        // user.setPhone("13817135911");
        Map<String, Object> map = userService.updateUser(user);
        System.out.println(map);
    }

}
