package cc.chinmoku.zkstudy.zk02;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperConnector {

    private ZooKeeper client;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    public ZooKeeper connect(String host) throws IOException, InterruptedException {

        client = new ZooKeeper(host, 5000, watcher -> {
            if (watcher.getState() == Watcher.Event.KeeperState.SyncConnected) {
                connectedSignal.countDown();
            }
        });

        connectedSignal.await();
        return client;
    }

    public void close() throws InterruptedException {
        client.close();
    }

}
