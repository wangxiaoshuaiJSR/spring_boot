package com.self.code.selectorleader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/7/17.
 */
public class LeaderSelectorClientC extends LeaderSelectorListenerAdapter implements Closeable {
    private String name;
    private LeaderSelector leaderSelector;
    private CountDownLatch countDownLatch=new CountDownLatch(1);

    public LeaderSelectorClientC(String name) {
        this.name = name;
    }

    public LeaderSelector getLeaderSelector() {
        return leaderSelector;
    }

    public void setLeaderSelector(LeaderSelector leaderSelector) {
        this.leaderSelector = leaderSelector;
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        //如果进入当前的方法，意味着当前的进程获得了锁。获得锁以后，这个方法会被回调
        //这个方法执行结束之后，表示释放leader权限
        System.out.println(name+"->现在是leader了");
        countDownLatch.await();
    }

    public static void main(String[] args) throws IOException {
        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString("192.168.5.138:2181").sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        curatorFramework.start();
        LeaderSelectorClientC leaderSelectorClient=new LeaderSelectorClientC("ClientC");
        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework,"/leaders",leaderSelectorClient);
        leaderSelector.start();
        System.in.read();

    }
}
