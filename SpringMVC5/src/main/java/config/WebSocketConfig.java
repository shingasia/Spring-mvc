package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import websocket.MyWebSocketHandler;

@Configuration
@EnableWebSocket
@ComponentScan(basePackages="websocket")
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private MyWebSocketHandler myWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myWebSocketHandler, "/myHandler").addInterceptors(new HttpSessionHandshakeInterceptor());
	}

}
