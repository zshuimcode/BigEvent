package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JWTTest {

    @Test
    public  void testGen(){

        Map<String, Object> map = new HashMap<>();

        map.put("id",1);
        map.put("username","张三");

        String token = JWT.create().withClaim("user",map) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))// 添加过期时间
                .sign(Algorithm.HMAC256("itheima")); // 指定算法配置密钥
        System.out.println(token);
    }

    @Test
    public void testParse(){
        String token= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MDE3MjMyMTAsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoi5byg5LiJIn19.YSqi_didGndaVLd46bA9wzMlzZGyGTSgNfTdbu7t05U";
        Map<String, Claim> itheima = JWT.require(Algorithm.HMAC256("itheima")).build().verify(token).getClaims();
        System.out.println(itheima.get("user"));
    }
}
