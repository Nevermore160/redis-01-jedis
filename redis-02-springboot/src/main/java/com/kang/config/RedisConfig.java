package com.kang.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate1(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //JSON序列化配置
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //String的序列化
        StringRedisSerializer serializer1 = new StringRedisSerializer();

        //key采用String的序列化方式
        template.setKeySerializer(serializer1);
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(serializer1);
        //value的序列化采用jackson
        template.setValueSerializer(serializer);
        //hash的序列化也采用jackson
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();

        return template;
    }
}
