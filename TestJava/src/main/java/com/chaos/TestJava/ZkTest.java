package com.chaos.TestJava;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkTest {

	// 会话超时时间，设置为与系统默认时间一致
	private static final int SESSION_TIMEOUT = 30 * 1000;

	// 创建 ZooKeeper 实例
	private ZooKeeper zk;

	// 创建 Watcher 实例
	private Watcher wh = new Watcher() {
		/**
		 * Watched事件
		 */
		@Override
		public void process(WatchedEvent event) {
			System.out.println("WatchedEvent >>> " + event.toString());
		}

	};

	// 初始化 ZooKeeper 实例
	private void createZKInstance() throws IOException {
		// System.setProperty("zookeeper.authProvider.1","org.apache.zookeeper.server.auth.SASLAuthenticationProvider");
		System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");
		System.setProperty("java.security.auth.login.config", "/home/chaos/FW/zookeeper-3.5.3-beta/conf/jaas.conf");
		// System.setProperty("keytab.file" ,
		// "/opt/zookeeper-myself/conf/zclient.keytab" );
		// 连接到ZK服务，多个可以用逗号分割写
		zk = new ZooKeeper("chaosX250:2181", 30000, this.wh);

	}

	private void ZKOperations() throws IOException, InterruptedException, KeeperException {
		// System.out.println("\n1. 创建 ZooKeeper 节点 (znode ： zoo2, 数据： myData2
		// ，权限：
		// OPEN_ACL_UNSAFE ，节点类型： Persistent");
		// zk.create("/znode3", "myData2".getBytes(), Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);

		System.out.println("\n2. 查看是否创建成功： ");
		System.out.println(new String(zk.getData("/znode3", null, null)));// 添加Watch

		// 前面一行我们添加了对/zoo2节点的监视，所以这里对/zoo2进行修改的时候，会触发Watch事件。
		System.out.println("\n3. 修改节点数据 ");
		zk.setData("/znode3", "shanhy".getBytes(), -1);

		// 这里再次进行修改，则不会触发Watch事件，这就是我们验证ZK的一个特性“一次性触发”，也就是说设置一次监视，只会对下次操作起一次作用。
		System.out.println("\n3-1. 再次修改节点数据 ");
		zk.setData("/znode3", "shanhy20160310-ABCDef".getBytes(), -1);

		System.out.println("\n4. 查看是否修改成功： ");
		System.out.println(new String(zk.getData("/znode3", false, null)));

		// System.out.println("\n5. 删除节点 ");
		// zk.delete("/znode3", -1);
		//
		// System.out.println("\n6. 查看节点是否被删除： ");
		// System.out.println(" 节点状态： [" + zk.exists("/znode3", false) + "]");
	}

	private void ZKClose() throws InterruptedException {
		zk.close();
	}

	public void createZKInsNormal() throws IOException {
		zk = new ZooKeeper("chaosX250:2181", 30000, null);
	}

	public void ZKOpNormal() throws KeeperException, InterruptedException {

		Stat st = new Stat();

		// sals
		// byte[] auInfo = "sasl:zkchaos/chaosX250@BOCO.COM:cdwra".getBytes();
		// zk.addAuthInfo("sals", auInfo);

		zk.setData("/testznode3", "shanhy".getBytes(), -1);
		byte[] ret = zk.getData("/testznode3", false, st);

		// digest auth
		// zk.addAuthInfo("digest", "tom:tom".getBytes());
		// byte[] ret = zk.getData("/testznode", false, st);

		System.out.println("result is : " + new String(ret));
	}

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZkTest dm = new ZkTest();
		dm.createZKInstance();
		// dm.ZKOperations();
		// dm.createZKInsNormal();
		dm.ZKOpNormal();
		dm.ZKClose();
	}
}