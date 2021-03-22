package com.yzz.rpcclient.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * @ClassName RPCClient
 * @Author yky
 * @Date 2021/3/20
 * @Version 1.0
 */
public class RPCClient<T> {

	public static <T> T getRemoteProxyObj(final String className, final InetSocketAddress addr) throws ClassNotFoundException {
		Class targetInterface = Class.forName(className);
		//将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
		return (T) Proxy.newProxyInstance(targetInterface.getClassLoader(), new Class<?>[]{targetInterface},
				new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket = null;
				ObjectOutputStream outputStream = null;
				ObjectInputStream inputStream = null;
				try{
					//创建socket客户端，根据指定地址连接远程服务器提供者
					socket = new Socket();
					socket.connect(addr);

					//需要调用远程服务者的类，方法名，参数列表编码后发送给远程服务提供者
					outputStream = new ObjectOutputStream(socket.getOutputStream());
					outputStream.writeUTF(targetInterface.getName());
					outputStream.writeUTF(method.getName());
					outputStream.writeObject(method.getParameterTypes());
					outputStream.writeObject(args);

					//同步阻塞等待远程服务器应答，获取应答结果后返回
					inputStream = new ObjectInputStream(socket.getInputStream());
					return inputStream.readObject();
				} finally {
					if(outputStream != null){
						outputStream.close();
					}
					if (inputStream != null){
						inputStream.close();
					}
					if(socket != null){
						socket.close();
					}
				}

			}
		});
	}
}
