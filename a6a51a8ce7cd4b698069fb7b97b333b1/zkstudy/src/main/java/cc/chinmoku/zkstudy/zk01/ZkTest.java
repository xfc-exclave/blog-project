package cc.chinmoku.zkstudy.zk01;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZkTest {

    private String connectString = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;

    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
            // 处理监视器任务：获取所有根节点
            List<String> children = null;
            try {
                children = zkClient.getChildren("/", true);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
            for (String child : children) {
                System.out.println(child);
            }
        });
    }

    @Test
    public void create() throws KeeperException, InterruptedException {
        String result = zkClient.create("/test", "node for test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void exists() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/test", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}