package ad.chat2.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
import ad.chat2.repositories.UserRepositories;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepositories userRepositories;

    @Mock
    private ConversationRepository conversationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();

        userRepositories.save(user);

        verify(userRepositories, times(1)).save(user);
    }

    @Test
    public void testFindUserByName_Found() {
        String name = "joe_doe";
        User user = new User();

        when(userRepositories.findUserByNick(name)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserByName(name);

        assertNotNull(foundUser);
        assertSame(user, foundUser);

    }

    @Test
    public void testFindUserByName_NotFound() {
        String name = "non_existent_user";

        when(userRepositories.findUserByNick(name)).thenReturn(Optional.empty());

        User foundUser = userService.findUserByName(name);

        assertNull(foundUser);

    }

    @Test
    public void testSaveUserInConversation() {
        Conversation conversation = new Conversation();

        conversationRepository.save(conversation);

        verify(conversationRepository, times(1)).save(conversation);
    }

}
