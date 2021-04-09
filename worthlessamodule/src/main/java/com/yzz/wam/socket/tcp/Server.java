package com.yzz.wam.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName Server
 * @Author yky
 * @Date 2021/4/9
 * @Version 1.0
 */
public class Server {
	
	public static void main(String[] args) {
		ServerSocket socket = null;
		OutputStream outputStream = null;
		BufferedReader bufferedReader = null;
		try {
			//建立基站
			socket = new ServerSocket(8899);
			//开始开启接收模式,接到后返回客户端的socket对象
			Socket client = socket.accept();
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String readLine = null;
			//字节缓冲区
//			byte[] bytes = new byte[1024];
//			String str = "";
//			int length = -1;
//			while((length = client.getInputStream().read(bytes, 0, bytes.length)) != -1){
//				str = str + new String(bytes, 0, bytes.length);
//			}
//			System.out.println(str);
			
			while((readLine = bufferedReader.readLine()) != null){
				System.out.println(readLine);
			}
			
//			//获取向客户端发送消息的对象流
//			outputStream = client.getOutputStream();
//			//向客户端写数据
//			outputStream.write("你连上了服务器...".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//注意关闭连接的条件
				if(outputStream != null){
					outputStream.close();
				}
				if(socket != null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
