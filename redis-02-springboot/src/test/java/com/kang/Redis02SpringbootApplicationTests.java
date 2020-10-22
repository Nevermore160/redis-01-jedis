package com.kang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate redisTemplate;


    @Test
    void contextLoads() {
        //redisTemplate.opsFor...有好多种，选择一些就行，opsForValue是操作字符串的
        redisTemplate.opsForValue().set("key1","value1");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        //也可以获取连接来操作
        /**获取redis连接对象，用的比较少
         *  RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
         *  connection.flushAll();
         *  connection.flushDb();
         */
    }

    @Test
    void test() throws JsonProcessingException {
        //真实开发会使用一个json来传递对象
        User user = new User("huikang", 22);
        String value = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("key1",value);
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

}
