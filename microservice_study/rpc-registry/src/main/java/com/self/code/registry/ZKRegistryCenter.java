package com.self.code.registry;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by Administrator on 2019/7/16.
 */
public class ZKRegistryCenter implements IRegistryCenter {

    CuratorFramework curatorFramework=null;
    {   //初始化zookeeper 会话超时5s  衰减重试
        curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("registry").build();
        curatorFramework.start();
    }
    public void registry(String serviceName, String serviceAdress)  {
        String servicePath ="/"+serviceName;
        try {
            if(curatorFramework.checkExists().forPath(servicePath)==null){
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath);
            }
            String addressPath = servicePath+"/"+serviceAdress;
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
            System.out.println("服务注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
