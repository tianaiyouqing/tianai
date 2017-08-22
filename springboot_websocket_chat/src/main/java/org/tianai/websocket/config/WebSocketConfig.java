package org.tianai.websocket.config;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
/**
 * Websocket 配置属性文件
 * 
 * <p>Title: WebSocketConfig</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	天爱有情
 * @date	2017年8月21日下午12:52:26
 * @version 1.0
 */

import org.springframework.context.annotation.Bean;
import org.tianai.websocket.controller.WSController;
public class WebSocketConfig implements ServerApplicationConfig {

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scand) {
		System.out.println("当前链接的人数是:" + scand.size());
		return scand;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
