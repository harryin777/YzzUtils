package com.yzz.rpcserver.service.impl;


import com.yzz.rpcserver.service.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ServiceCenter
 * @Author yky
 * @Date 2021/3/20
 * @Version 1.0
 */
@Slf4j
@Service
public class ServiceCenter implements Server {


	/**
	 * 这里应该是获取起当前可用的处理器数量作为了线程池的线程数
	 */
	private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	/**
	 * hashmap用来存储CallId 到函数的一个映射
	 */
	private static final HashMap<String, Class> serviceRegistry = new HashMap<>();

	private static boolean isRunning = false;

	private static int port;

	//public ServiceCenter(int port){
//		ServiceCenter.port = port;
//	}
	@Override
	public void start() throws IOException {
		log.info("server start");
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress(port));
		try{
			while(true){
				executorService.execute(new ServiceTask(serverSocket.accept()));
			}
		}finally {
			serverSocket.close();
		}

	}

	@Override
	public void register(Class serviceInterface, Class impl) {
		serviceRegistry.put(serviceInterface.getName(), impl);
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void stop() {
		isRunning = false;
		executorService.shutdown();
	}

	private static class ServiceTask implements Runnable{

		Socket client = null;

		public ServiceTask(Socket client){
			client = client;
		}

		@Override
		public void run() {
			log.info("服务器端的内容已经启动");
			ObjectInputStream inputStream = null;
			ObjectOutputStream outputStream = null;

			try {
				inputStream = new ObjectInputStream(client.getInputStream());
				String serviceName = inputStream.readUTF();
				String methodName = inputStream.readUTF();
				//获取参数类型
				Class<?> [] parameterTypes =(Class<?>[]) inputStream.readObject();
				//获取参数
				Object[] arguments = (Object[]) inputStream.readObject();
				Class serviceClass = serviceRegistry.get(serviceName);
				if(serviceClass == null){
					throw new ClassNotFoundException(serviceName + "not Found!!!");
				}
				//利用反射获取方法
				Method method =serviceClass.getMethod(methodName, parameterTypes);
				Object result = method.invoke(serviceClass.newInstance(), arguments);

				//输出流把结果返回
				outputStream = new ObjectOutputStream(client.getOutputStream());
				outputStream.writeObject(result);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//关闭各种流
				if(outputStream != null){
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(client != null){
					try {
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}
