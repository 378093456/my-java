package com.test;

import com.test.mapper.User1Mapper;
import com.test.mapper.User2Mapper;
import com.test.model.User1;
import com.test.model.User2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Test
    void contextLoads() {
        User1 user1=new User1();
        user1.setName("张三");
        user1.setAge(18);
        user1Mapper.insert(user1);
        insert();
    }

    //局部事物提交
    @Transactional
    void insert(){
        User2 user2=new User2();
        user2.setName("李四");
        user2.setAge(18);
        user2Mapper.insert(user2);
    }
}
