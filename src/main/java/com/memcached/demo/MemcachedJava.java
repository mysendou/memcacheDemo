package com.memcached.demo;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.net.InetSocketAddress;

/**
 * Created by Jeff.Ma on 3/19/2018.
 */
public class MemcachedJava {
    public static void main(String[] args) throws Exception{
        // 本地连接 Memcached 服务
        MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));

        System.out.println("Connection to server sucessfully");
        // 存储数据
        OperationFuture<Boolean> fo = mcc.set("jeff", 900, "Hello Memcached");

        // 查看存储状态
        System.out.println("set status:" + fo.get());

        // 输出值
        System.out.println("jeff value in cache - " + mcc.get("jeff"));

        System.out.println("==========================================");

        fo = mcc.set("jeff", 900, "Memcached");

        // 查看存储状态
        System.out.println("set status:" + fo.get());

        // 输出值
        System.out.println("jeff value in cache - " + mcc.get("jeff"));

        System.out.println("==========================================");

        /** 测试添加 **/
        // 添加新key
        fo = mcc.add("jeff1", 900, "Jeff1 test memcached");

        // 打印状态
        System.out.println("add status:" + fo.get());

        // 输出
        System.out.println("jeff1 value in cache - " + mcc.get("jeff1"));

        System.out.println("==========================================");

        /** 测试替换 **/
        // 替换key
        fo = mcc.replace("jeff1", 900, "Jeff1 test memcached replace");

        // 打印状态
        System.out.println("add status:" + fo.get());

        // 输出
        System.out.println("jeff1 value in cache - " + mcc.get("jeff1"));

        // 清除
        mcc.flush().isDone();
        // 关闭连接
        mcc.shutdown();
    }
}
