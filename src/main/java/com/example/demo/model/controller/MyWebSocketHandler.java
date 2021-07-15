package com.example.demo.model.controller;

import com.example.demo.model.AdminRepository;
import com.example.demo.model.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.ArrayList;


@Controller
@CrossOrigin(origins = "*")
public class MyWebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private UsersRepository usersRepository;


String str;


    @GetMapping("/test")
    public void test (){

        str = usersRepository.getUserWithId(2);

        System.out.println("FIRST" + str);
    }





    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message );
        System.out.println("Message" + message.getPayload());

        System.out.println("SECOND" + str);
      session.sendMessage(new TextMessage("abc"));
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // super.handleTransportError(session, exception);
        System.out.println("TRANSPORT ERROR");
    }
}
