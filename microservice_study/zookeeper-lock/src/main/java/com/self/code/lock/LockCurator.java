package com.self.code.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Administrator on 2019/7/17.
 */
public class LockCurator {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        InterProcessMutex lock = new InterProcessMutex(curatorFramework,"/locks");
        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"开始竞争锁");
                try {
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName()+"成功获取到锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"Thread->"+i).start();
        }
    }
}
