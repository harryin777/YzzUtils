package com.yzz.rpcserver.service;

import java.io.IOException;

/**
 * @ClassName Server
 * @Author yky
 * @Date 2021/3/20
 * @Version 1.0
 */
public interface Server {

	void start() throws IOException;

	void register(Class serviceInterface, Class impl);

	boolean isRunning();

	int getPort();

	void stop();
}
