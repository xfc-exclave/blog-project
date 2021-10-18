package cc.chinmoku.zkstudy.zk03;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ZkCurator {

    private final String connectString = "hadoop01:2181,hadoop02:2181";

    @Test
    public void test() throws Exception {
        // 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        // 创建客户端，方式一
//        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, 5000, 5000, retryPolicy);

        // 创建客户端，方式二
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(5000)  // 会话超时时间
                .connectionTimeoutMs(5000) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace("base") // 包含隔离名称
                .build();
        client.start();
        System.out.println("已创建并启动客户端");

        // 创建数据节点
        client.create().creatingParentContainersIfNeeded() // 递归创建所需父节点
                .withMode(CreateMode.PERSISTENT) // 创建类型为持久节点
                .forPath("/nodeA", "init".getBytes()); // 目录及内容
        System.out.println("成功创建持久节点");

        // 获取节点数据
        byte[] bytes = client.getData().forPath("/nodeA");
        System.out.println(new String(bytes));// init

        // 修改节点数据
        client.setData()
                .withVersion(0) // 指定版本修改
                .forPath("/nodeA", "data".getBytes());

        // 事务处理
        Collection<CuratorTransactionResult> commit = client.inTransaction().check().forPath("/nodeA")
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/nodeB", "init".getBytes())
                .and()
                .create().withMode(CreateMode.EPHEMERAL).forPath("/nodeC", "init".getBytes())
                .and()
                .commit();
        commit.forEach(c -> {
            System.out.println("Path: " + c.getForPath());
        });

        Stat stat = client.checkExists().forPath("/nodeA");// 检查是否存在
        if (stat != null) {
            System.out.println("/base/nodeA 节点存在。");
        }
        List<String> strings = client.getChildren().forPath("/nodeA");// 获取子节点的路径
        for (String string : strings) {
            System.out.println("path: " + string);
        }
        // 异步回调
        Executor executor = Executors.newFixedThreadPool(2);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground((curatorFramework, curatorEvent) -> {
                    System.out.println(String.format("eventType:%s,resultCode:%s", curatorEvent.getType(), curatorEvent.getResultCode()));
                }, executor)
                .forPath("/syncNode");
        Thread.sleep(2000);

        // 删除数据节点
        client.delete()
                .guaranteed()  // 强制保证删除
                .deletingChildrenIfNeeded() // 递归删除子节点
                .withVersion(0) // 指定删除的版本号
                .forPath("/");
        System.out.println("已成功删除节点[/base]");
    }
}