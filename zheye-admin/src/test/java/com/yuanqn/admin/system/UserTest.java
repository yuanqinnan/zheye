package com.yuanqn.admin.system;

import com.pugwoo.wooutils.redis.RedisHelper;
import com.yuanqn.admin.common.enums.UserSexEnum;
import com.yuanqn.admin.common.enums.UserStatusEnum;
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
    public void test() throws Exception {
        userService.register("admin","123456");
    }

    @Test
    public void redisTest() {
        redisHelper.setString("admin:123", 2000, "456");
    }
}
