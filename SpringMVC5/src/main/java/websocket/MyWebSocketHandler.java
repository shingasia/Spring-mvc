package websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//ChatHandler
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
	
	//List또는 Map으로 웹 소켓 세션을 관리하는 객체를 만든다
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionExtablished");
	}
	
	//session에서 메시지를 수신했을 때 실행된다. message 타입에 따라 handleTextMessage(), handleBinaryMessage()를 실행한다.
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String clientMessage = message.getPayload();
		
		if (clientMessage.startsWith("Hello") || clientMessage.startsWith("Hi")) {
			session.sendMessage(new TextMessage("Hello! What can i do for you?"));
		}else {
			session.sendMessage(new TextMessage("This is a simple hello world example of using Spring WebSocket."));
		}
		
		//session.getId()
		//session.sendMessage(message);
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
	}
	
}
