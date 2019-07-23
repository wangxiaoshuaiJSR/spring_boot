package com.self.code;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by Administrator on 2019/7/16.
 */
public class WatchZookeeperNode {
    //PathChildCache 针对子节点的创建、删除、更新。触发事件
    //NodeCache  针对当前节点的变化触发事件
    //TreeCache  综合事件

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        addListenerWithChild(curatorFramework);
        System.in.read();

    }

    //监听本节点
    private static void addListenerWithNode(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache=new NodeCache(curatorFramework,"/watch",false);
        NodeCacheListener nodeCacheListener =()->{
            System.out.println("receive node change");
            System.out.println(nodeCache.getCurrentData().
                    getPath()+"-"+new String(nodeCache.getCurrentData().getData()));
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }

    //监听子节点
    private static void addListenerWithChild(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache nodeCache=new PathChildrenCache(curatorFramework,"/watch",true);
        PathChildrenCacheListener nodeCacheListener =(curatorFramework1,pathChildrenCacheEvent)->{
            System.out.println(pathChildrenCacheEvent.getType()+"-"+
                    new String(pathChildrenCacheEvent.getData().getData()));

        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
