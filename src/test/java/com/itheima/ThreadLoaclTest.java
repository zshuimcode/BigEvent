package com.itheima;

import org.junit.jupiter.api.Test;

public class ThreadLoaclTest {
    @Test
    public void testThreadLocalSetAndGet(){
        ThreadLocal threadLocal = new ThreadLocal();

         new Thread(()-> {
        threadLocal.set("雄胺盐");
             System.out.println("蓝色"+threadLocal.get());
             System.out.println("蓝色"+threadLocal.get());
             System.out.println("蓝色"+threadLocal.get());
        },"蓝色").start();

        new Thread(()-> {
            threadLocal.set("啥撒");
            System.out.println("红色"+threadLocal.get());
            System.out.println("红色"+threadLocal.get());
            System.out.println("红色"+threadLocal.get());
        },"红色").start();

    }
}
