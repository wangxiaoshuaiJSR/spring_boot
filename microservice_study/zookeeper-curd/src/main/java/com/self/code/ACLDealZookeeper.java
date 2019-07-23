package com.self.code;



import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxiaoshuai on 2019/7/16.
 * zookeeper的权限管理 代码
 */
public class ACLDealZookeeper {
    //192.168.5.138:2181,192.168.5.136:2181 集群配置
    public static void main(String[] args) throws Exception {
        demo2();
    }
    private static void demo2() throws Exception {
        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        List<ACL> list = new ArrayList<>();
        //通过密码来验证/  xshell控制台也可以这样来搞  也可以通过ip来授权
        ACL acl=new ACL(ZooDefs.Perms.READ|ZooDefs.Perms.WRITE,new Id("digest",
                DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);
        //对已经存在的/temp节点增加权限
        curatorFramework.setACL().withACL(list).forPath("/temp");
    }

    /*private static void demo1(){
        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        List<ACL> list = new ArrayList<>();
        //通过密码来验证/  xshell控制台也可以这样来搞
        ACL acl=new ACL(ZooDefs.Perms.READ|ZooDefs.Perms.WRITE,new Id("digest",
                DigestAuthenticationProvider.generateDigest("admin:admin")));
        list.add(acl);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list).forPath("/auth");
    }*/

}
