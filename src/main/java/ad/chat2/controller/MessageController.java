package ad.chat2.controller;

import java.time.LocalDate;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ad.chat2.dto.MessageDto;
import ad.chat2.model.Conversation;
import ad.chat2.model.Message;
import ad.chat2.model.User;
import ad.chat2.repositories.MessageRepositories;
import ad.chat2.service.MessageService;

@Controller
public class MessageController {

    private MessageRepositories messageRepositories;
    private MessageService messageService;

    public MessageController(MessageRepositories messageRepositories, MessageService messageService) {
        this.messageRepositories = messageRepositories;
        this.messageService = messageService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/ws")
    public MessageDto sendMessage(MessageDto messageDto) {

        String messageContent = messageDto.getMessage();
        String messageSender = messageDto.getNick();
        Long messageId = messageDto.getIdConversation();

        // Content
        Message message = new Message();
        message.setContent(messageContent);
        // Data
        LocalDate date = LocalDate.now();
        message.setDate(date);
        // Użytkownik wysyłający wiadomość
        User sender = messageService.findSenderByNick(messageSender);
        message.setSender(sender);
        // Conversation
        Conversation conversation = messageService.findConversationById(messageId);
        message.setConversation(conversation);

        messageRepositories.save(message);

        return messageDto;

    }

}
