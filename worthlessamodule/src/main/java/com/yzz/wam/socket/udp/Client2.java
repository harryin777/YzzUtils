package com.yzz.wam.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName Client2
 * @Author yky
 * @Date 2021/4/9
 * @Version 1.0
 */
public class Client2 {
	public static void main(String[] args) {
		try {
			// （1）创建发送端socket对象
			// public DatagramSocket(int port)：创建数据报套接字并将其绑定到本地主机上的指定端口。
			DatagramSocket ds = new DatagramSocket(10086);
			
			// （2）创建一个数据包（接收容器）
			// DatagramPacket(byte[] buf, int length)：构造DatagramPacket，用来接收长度为
			// length 的数据包。
			byte[] bys = new byte[1024];
			int len = bys.length;
			
			DatagramPacket dp = new DatagramPacket(bys, len);
			
			// （3）调用socket对象的接收方法接收数据
			// public void receive(DatagramPacket p)：从此套接字接收数据报包。
			ds.receive(dp);
			
			// （4）解析数据并显示在控制台
			// 获取对方的IP
			// public InetAddress getAddress()：返回某台机器的 IP地址，此数据报将要发往该机器或者是从该机器接收到的。
			InetAddress address = dp.getAddress();
			String ip = address.getHostAddress();
			
			// public byte[] getData()：返回数据缓冲区。
			// public int getLength()：返回将要发送或接收到的数据的长度。
			byte[] bys2 = dp.getData();
			int len2 = dp.getLength();
			String s = new String(bys2, 0, len2);
			System.out.println(ip + ":" + s);
			
			// （5）释放资源
			ds.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}
}
