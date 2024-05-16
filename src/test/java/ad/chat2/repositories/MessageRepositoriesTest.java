package ad.chat2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.chat2.model.Message;
import ad.chat2.service.MessageService;

public class MessageRepositoriesTest {

    @Mock
    MessageRepositories messageRepositories;

    @InjectMocks
    MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findMessegesByConversationId_True() {
        List<Message> messageList = new ArrayList<>();
        Long id = 1L;

        messageList.add(new Message());
        messageList.add(new Message());

        when(messageRepositories.findMessegesByConversationId(id)).thenReturn(messageList);

        List<Message> foudnMessages = messageRepositories.findMessegesByConversationId(id);

        assertEquals(2, foudnMessages.size());

    }

    @Test
    public void findMessegesByConversationId_False() {
        List<Message> messageList = new ArrayList<>();
        Long id = 2L;

        when(messageRepositories.findMessegesByConversationId(id)).thenReturn(messageList);

        List<Message> foundMessages = messageRepositories.findMessegesByConversationId(id);

        assertEquals(0, foundMessages.size());
    }
}
