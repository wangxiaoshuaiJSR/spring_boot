package com.self.code.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/7/16.
 */
public class ZKDiscovery implements IServiceDiscovery {
    CuratorFramework curatorFramework=null;
    List<String> list=new ArrayList<>(); //zookeeper上的东西读取到此
    {   //初始化zookeeper 会话超时5s  衰减重试
        curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("registry").build();
        curatorFramework.start();
    }
    public String discovery(String serviceName) {
        try {
            list = curatorFramework.getChildren().forPath("/helloService");
            registryWatch("/helloService");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectList(list);
    }

    private void registryWatch(String path) throws Exception {
        PathChildrenCache nodeCache=new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener nodeCacheListener =(curatorFramework1, pathChildrenCacheEvent)->{
           list=curatorFramework1.getChildren().forPath(path);
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start(PathChildrenCache.StartMode.NORMAL);
    }

    String selectList(List<String> list){
        if(list.size()==0){
            return null;
        }
        if(list.size()==1){
            return list.get(0);
        }
        int length = list.size();
        Random random=new Random();
        return list.get(random.nextInt(length));
    }
}
