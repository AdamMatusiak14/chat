package ad.chat2.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.chat2.repositories.ConversationRepository;
import ad.chat2.repositories.UserRepositories;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepositories userRepositories;

    @Mock
    ConversationRepository conversationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
