package ad.chat2.controller;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.chat2.dto.MessageDto;
import ad.chat2.model.Conversation;
import ad.chat2.model.Message;
import ad.chat2.model.User;
import ad.chat2.repositories.MessageRepositories;
import ad.chat2.service.MessageService;

public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @Mock
    private MessageRepositories messageRepositories;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // sendMessage
    // findSenderByNick
    // findCoversationById
    // save

    @Test
    public void testSendMessage() {
        User user = new User();
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage("Hello everybody");
        messageDto.setNick("Adam");
        messageDto.setIdConversation(1L);

        User sender = new User();
        sender.setNick("Adam");

        Conversation conversation = new Conversation();
        conversation.setId(1L);

        when(messageService.findSenderByNick("Adam")).thenReturn(user);

        when(messageService.findConversationById(1L)).thenReturn(conversation);

        // zapisa do bazy message
        Message message = new Message();
        message.setContent(messageDto.getMessage());
        message.setDate(LocalDate.now());
        message.setSender(user);
        message.setConversation(conversation);

        messageRepositories.save(message);

        verify(messageRepositories, times(1)).save(any(Message.class));

    }

    @Test
    public void testFindSenderByNick() {

        User user = new User();
        MessageDto messageDto = new MessageDto();

        when(messageService.findSenderByNick(messageDto.getNick())).thenReturn(user);

    }

    @Test
    public void testFindConversationById() {
        MessageDto messageDto = new MessageDto();
        Conversation conversation = new Conversation();

        when(messageService.findConversationById(messageDto.getIdConversation())).thenReturn(conversation);

    }
}
