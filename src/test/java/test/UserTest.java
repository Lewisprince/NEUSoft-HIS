package test;

import com.forwawrd.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.forwawrd.service.UserService;

import java.util.Date;
import java.util.List;

// 用户的业务层测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext*.xml")
public class UserTest {

    @Autowired
    private UserService userService;


    @Test
    public void TestFindAll()
    {
        List<User> users = userService.findAll();
        System.out.println(users);
    }

    @Test
    public void TestFindByID()
    {
        User user = userService.findByID(1);
        System.out.println(user);
    }

    @Test
    public void TestFindByName()
    {
        User user = userService.findByName("扁鹊");
        System.out.println(user);
    }

    @Test
    public void TestUpdate()
    {
        User changed = userService.findByID(1);
        System.out.println(changed);
        changed.setOffice(1);
        userService.updateUser(changed);
        System.out.println(userService.findByID(1));
    }
    @Test
    public void findOffice()
    {
        List<String> test = userService.findAllOffice();
        System.out.println(test);
    }

    @Test
    public void findDoctors()
    {
        List<String> test = userService.findDoctors("心血管内科", "主治医师");
        System.out.println(test);
    }
//
//    @Test
//    public void testRegistration()
//    {
//        List<String> test = userService.registration("210105182734561919","Lweqwe",1,new Date(),13,1,"asdaasdads",
//                1);
//        System.out.println(test);
//    }
}
