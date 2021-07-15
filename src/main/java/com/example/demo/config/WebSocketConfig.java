package com.example.demo.config;

import com.example.demo.model.controller.MyWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
//@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  /*  @Override
    public void configureMessageBroker (MessageBrokerRegistry config){

        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");


    }

    @Override
    public void registerStompEndpoints (StompEndpointRegistry registry){

        registry.addEndpoint("/abcabc");
        registry.addEndpoint("/abcabc").withSockJS();

    }*/

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new MyWebSocketHandler(), "/abcabc");
    }
}


