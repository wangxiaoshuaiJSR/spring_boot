package com.self.code.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Administrator on 2019/7/17.
 */
public class LockCurator1 {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");
        System.out.println("开始竞争锁");
        lock.acquire();
        Thread.sleep(40000);
        lock.release();
        System.out.println("释放锁");
    }
}
