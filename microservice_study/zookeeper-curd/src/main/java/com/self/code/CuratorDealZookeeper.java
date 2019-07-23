package com.self.code;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by wangxiaoshuai on 2019/7/16.
 */
public class CuratorDealZookeeper {
    //192.168.5.138:2181,192.168.5.136:2181 集群配置
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework=CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        //create(curatorFramework);
        update(curatorFramework);
        //curatorFramework.create();c
        //curatorFramework.getData();R
        //curatorFramework.setData();U
        //curatorFramework.delete();D
    }

    private static void create(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .forPath("/data/program","test".getBytes());
    }

    private static void update(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().forPath("/data/program","update".getBytes());
    }

    //绝对的安全删除，加入了版本号
    private static void delete(CuratorFramework curatorFramework) throws Exception {
        Stat stat=new Stat();
        String value = new String(curatorFramework.getData().storingStatIn(stat).forPath("/data/program"));
        curatorFramework.delete().withVersion(stat.getVersion()).forPath("/data/program");
    }



}
