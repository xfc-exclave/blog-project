package cc.chinmoku.zkstudy.zk02;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZkCreate {

    private static ZooKeeper zk;

    private static ZooKeeperConnector conn;

    public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String[] args) {

        String path = "/MyZnode";

        byte[] data = "My Znode".getBytes();

        try {
            conn = new ZooKeeperConnector();
            zk = conn.connect("hadoop01:2181,hadoop02:2181,hadoop03:2181");
            create(path, data);
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
