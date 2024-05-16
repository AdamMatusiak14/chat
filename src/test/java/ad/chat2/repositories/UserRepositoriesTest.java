package ad.chat2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ad.chat2.model.User;
import ad.chat2.service.UserService;

//@DataJpaTest
public class UserRepositoriesTest {

    @Mock
    private UserRepositories userRepositories;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void tesFindUserByNick_True() {
        User user = new User();
        user.setNick("testUser");

        // userRepositories.save(user);
        when(userRepositories.findUserByNick("testUser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepositories.findUserByNick("testUser");

        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getNick());
    }

    @Test
    public void testFindUser_False() {
        when(userRepositories.findUserByNick(anyString())).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepositories.findUserByNick("nonExistUser");

        assertEquals(Optional.empty(), foundUser);
    }

}
