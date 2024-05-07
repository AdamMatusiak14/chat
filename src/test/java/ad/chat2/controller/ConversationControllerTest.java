package ad.chat2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.ui.Model;

import ad.chat2.dto.DtoConversation;
import ad.chat2.model.Conversation;
import ad.chat2.model.Message;
import ad.chat2.model.User;
import ad.chat2.service.ConversationService;

public class ConversationControllerTest {

    @InjectMocks
    private ConversationController conversationController;

    @Mock
    private ConversationService conversationService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConversation() {
        String viewName = conversationController.conversation(model);

        assertEquals("findConversation", viewName);

        verify(model).addAttribute(eq("convertDto"), any(DtoConversation.class));
    }

    @Test
    public void testFindConversation() {
        // model
        // convertDto //wejscie
        User user1 = new User();
        User user2 = new User();
        DtoConversation convertDto = new DtoConversation("Adam", "Gaja");
        Conversation conversation = new Conversation();
        List<Message> messages = new ArrayList<>();

        when(conversationService.findUser(convertDto.getFirstName())).thenReturn(user1);
        when(conversationService.findUser(convertDto.getSecondName())).thenReturn(user2);

        when(conversationService.findConversation(user1, user2)).thenReturn(conversation);

        when(conversationService.findMessage(anyLong())).thenReturn(messages);

        String viewName = conversationController.findConversation(convertDto, model);

        assertEquals("/viewConversation", viewName);

    }

}
