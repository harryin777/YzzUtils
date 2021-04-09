package com.yzz.wam.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName Client1
 * @Author yky
 * @Date 2021/4/9
 * @Version 1.0
 */
public class Client1 {
	public static void main(String[] args) {
		try{
			//（1）创建发送端socket对象
			// DatagramSocket()：构造数据报套接字并将其绑定到本地主机上任何可用的端口。
			DatagramSocket ds = new DatagramSocket();
			
			//（2）创建数据并把数据打包
			// public DatagramPacket(byte[] buf,int length,InetAddress address,int port)
			// 1-创建数据
			byte[] bys = "hello UDP，我来了".getBytes();
			// 2-长度
			int len = bys.length;
			// 3-IP地址
			InetAddress address = InetAddress.getByName("localhost");
			// 4-端口
			int port = 10086;
			DatagramPacket dp = new DatagramPacket(bys,len,address,port);
			
			//（3）调用socket对象的发送方法发送数据包
			ds.send(dp);
			
			//（4）释放资源
			ds.close();
		}catch (Exception e){
			System.out.println("有异常");
		}
		
	}
}
