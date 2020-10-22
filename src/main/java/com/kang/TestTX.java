package com.kang;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","huikang");

        Transaction multi = jedis.multi(); //开启事务

        String result = jsonObject.toJSONString();
        try {
            multi.set("key1",result);
            multi.set("key2",result);

            multi.exec(); //执行事务
        }catch (Exception e){
            multi.discard(); //放弃事务
            e.printStackTrace();
        }finally {
            System.out.println(multi.get("key1"));
            System.out.println(multi.get("key2"));
            jedis.close(); //关闭连接
        }
    }
}
