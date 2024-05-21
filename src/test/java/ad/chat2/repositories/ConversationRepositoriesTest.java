package ad.chat2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.service.ConversationService;

public class ConversationRepositoriesTest {

    @Mock
    private ConversationRepository conversationRepository;

    @InjectMocks
    private ConversationService conversationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findConversationById_True() {
        Conversation conversation = new Conversation();
        conversation.setId(1L);

        when(conversationRepository.findConversationById(1L)).thenReturn(Optional.of(conversation));

        Optional<Conversation> foundConversation = conversationRepository.findConversationById(1L);

        assertTrue(foundConversation.isPresent());

        assertEquals(1L, foundConversation.get().getId());
    }

    @Test
    public void findConversationById_False() {
        Long id = 2L;

        when(conversationRepository.findConversationById(2L)).thenReturn(Optional.empty());

        Optional<Conversation> foundConversation = conversationRepository.findConversationById(2L);

        assertEquals(Optional.empty(), foundConversation);

    }

    @Test
    public void findConversationByUs1AndUs2_True() {
        User user1 = new User();
        User user2 = new User();
        user1.setId(1L);
        user2.setId(2L);

        Conversation conversation = new Conversation();
        conversation.setUs1(user1);
        conversation.setUs2(user2);

        when(conversationRepository.findConversationByUs1AndUs2(user1, user2)).thenReturn(Optional.of(conversation));

        Optional<Conversation> foudnConversation = conversationRepository.findConversationByUs1AndUs2(user1, user2);

        assertTrue(foudnConversation.isPresent());
        assertEquals(user1, foudnConversation.get().getUs1());

        assertEquals(user2, foudnConversation.get().getUs2());

    }

    @Test
    public void findConversationByUs1AndUs2_False() {
        User user1 = new User();
        User user2 = new User();
        user1.setId(1L);
        user2.setId(2L);

        when(conversationRepository.findConversationByUs1AndUs2(user1, user2)).thenReturn(Optional.empty());

        Optional<Conversation> foundConversation = conversationRepository.findConversationByUs1AndUs2(user1, user2);

        assertFalse(foundConversation.isPresent());

    }

}
