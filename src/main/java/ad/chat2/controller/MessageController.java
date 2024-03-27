package ad.chat2.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ad.chat2.dto.MessageDto;
import ad.chat2.model.Message;
import ad.chat2.repositories.MessageRepositories;

@Controller
public class MessageController {

    private MessageRepositories messageRepositories;

    public MessageController(MessageRepositories messageRepositories) {
        this.messageRepositories = messageRepositories;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/ws")
    public void sendMessage(MessageDto messageDto) {

        String messageContent = messageDto.getMessage();

        Message message = new Message();
        message.setContent(messageContent);
        messageRepositories.save(message);
        System.out.println("Cześć jestem klasą saveMessage");
    }

    // - Id
    // - String Content
    // - LocalDate Date
    // - User Sender
    // Conversation conversation
}
