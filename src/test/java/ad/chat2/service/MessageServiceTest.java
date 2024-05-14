package ad.chat2.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.repositories.ConversationRepository;
import ad.chat2.repositories.MessageRepositories;
import ad.chat2.repositories.UserRepositories;
import jakarta.persistence.EntityNotFoundException;

public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private UserRepositories userRepositories;

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private MessageRepositories messageRepositories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindSenderByNick_Found() {
        User user = new User();
        String name = "joe_doe";

        when(userRepositories.findUserByNick(name)).thenReturn(Optional.of(user));

        User foundUser = messageService.findSenderByNick(name);

        assertNotNull(foundUser);
        assertSame(user, foundUser);

    }

    @Test
    public void testFindSenderByNick_NotFound() {

        String name = "non_exist_user";

        when(userRepositories.findUserByNick(name)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            messageService.findSenderByNick(name);
        });

        assertTrue(thrown.getMessage().contains("Nie znaleziono encji o identyfikatorze " + name));
        verify(userRepositories, times(1)).findUserByNick(name);
    }

    @Test
    public void testFindConversationById_Found() {
        Conversation conversation = new Conversation();
        Long id = 1L;

        when(conversationRepository.findConversationById(id)).thenReturn(Optional.of(conversation));

        Conversation foundConversation = messageService.findConversationById(id);

        assertNotNull(foundConversation);
        assertSame(conversation, foundConversation);
    }

    @Test
    public void testFindConversationById_NotFound() {
        Long id = 1L;

        when(conversationRepository.findConversationById(id)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            messageService.findConversationById(id);
        });

        assertTrue(thrown.getMessage().contains("Nie znaleziono encji o identyfikatorze " + id));

        verify(conversationRepository, times(1)).findConversationById(id);

    }

}
