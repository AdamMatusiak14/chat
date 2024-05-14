package ad.chat2.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

public class ConversationServiceTest {

    @InjectMocks
    private ConversationService conversationService;

    @Mock
    private ConversationRepository conversationRepository;

    @Mock
    private UserRepositories userRepositories;

    @Mock
    private MessageRepositories messageRepositories;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUser_True() {

        User user = new User();
        String name = "joe_doe";

        when(userRepositories.findUserByNick(name)).thenReturn(Optional.of(user));

        User foundUser = conversationService.findUser(name);

        assertNotNull(foundUser);
        assertSame(user, foundUser);

    }

    @Test
    public void testFindUser_False() {

        String name = "not_exist_user";

        when(userRepositories.findUserByNick(name)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            conversationService.findUser(name);
        });

        assertTrue(thrown.getMessage().contains("Nie znaleziono encji o identyfikatorze" + name));

        verify(userRepositories, times(1)).findUserByNick(name);

    }

    @Test
    public void testFindConversationById_Found() {
        User user1 = new User();
        User user2 = new User();
        Conversation conversation = new Conversation();

        when(conversationRepository.findConversationByUs1AndUs2(user1, user2)).thenReturn(Optional.of(conversation));

        Conversation foundConversation = conversationService.findConversation(user1, user2);

        assertNotNull(foundConversation);
        assertSame(conversation, foundConversation);
    }

    @Test
    public void testFindConversationById_NotFound() {
        User user1 = new User();
        User user2 = new User();

        when(conversationRepository.findConversationByUs1AndUs2(user1, user2)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            conversationService.findConversation(user1, user2);
        });

        assertTrue(thrown.getMessage().contains("Nie znaleziono encji conversation"));

        verify(conversationRepository, times(1)).findConversationByUs1AndUs2(user1, user2);
    }

}
