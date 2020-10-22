package com.kang;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379); //连接本地，我没装
        //redis的所有命令都在new出来的Jedis对象中有，redis中的指令，就是这里面的方法
        jedis.sadd("key1","value1");
        jedis.exists("key1");
        //.....
        jedis.close();//关闭连接
    }
}
