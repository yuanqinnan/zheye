package com.yuanqn.admin.system;

import com.pugwoo.wooutils.redis.RedisHelper;
import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuanqn
 * @date 2019/7/14 0:12
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    IUserService userService;

    @Autowired
    RedisHelper redisHelper;


    @Test
    public void test(){
        User user=new User();
        user.setDeptId(1L);
        user.setMail("1234@163.com");
        user.setName("张三");
        user.setUsername("admin");
        user.setPhone("18948329231");
        user.setSex("MAN");
        userService.createUser(user);
    }

    @Test
    public void redisTest(){
        redisHelper.setString("admin:123",2000,"456");
    }
}