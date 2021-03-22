package com.yzz.rpcclient.controller;

import com.yzz.rpcclient.rpc.RPCClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetSocketAddress;

/**
 * @ClassName RPCClientController
 * @Author yky
 * @Date 2021/3/20
 * @Version 1.0
 */
@Controller
public class RPCClientController {

	@GetMapping("/testRPC")
	@ResponseBody
	public String testRPC() throws ClassNotFoundException {
		//第一个参数需要server端的全类名
		Object o = RPCClient.getRemoteProxyObj("com.yzz.rpcserver.service.ServiceForTest", new InetSocketAddress("localhost", 8080));
		return o.toString();
	}
}
