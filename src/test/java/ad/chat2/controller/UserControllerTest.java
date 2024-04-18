package ad.chat2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.ui.Model;

import ad.chat2.dto.DtoUsers;
import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.service.UserService;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        String viewName = userController.addUser(model);

        assertEquals("addUser", viewName);
        verify(model).addAttribute(eq("user"), any(User.class));
    }

    @Test
    public void testSaveUser() {

        User user = new User();

        String viewName = userController.saveUser(user);

        assertEquals("redirect:/", viewName);

        verify(userService).saveUser(user);

    }

    @Test
    public void testStartChat() {
        String viewName = userController.startChat(model);

        assertEquals("/chat", viewName);

        verify(model).addAttribute(eq("userDto"), any(DtoUsers.class));
    }

    @Test
    public void testCheckUser_UserNotFound() {
        User user1 = new User();
        User user2 = new User();
        DtoUsers userDto = new DtoUsers(user1, user2);

        when(userService.findUserByName(anyString())).thenReturn(null);

        String viewName = userController.checkUser(userDto, model);

        assertEquals("redirect:chat", viewName);

        verify(userService, never()).saveUserInConversation(any(Conversation.class));
        verify(model, never()).addAttribute(eq("conversation"), any(Conversation.class));

    }

    @Test
    public void testCheckUser_UserFound() {
        User user1 = new User();
        User user2 = new User();
        DtoUsers userDto = new DtoUsers(user1, user2);

        when(userService.findUserByName(user1.getNick())).thenReturn(user1);
        when(userService.findUserByName(user2.getNick())).thenReturn(user2);

        String viewName = userController.checkUser(userDto, model);

        // assert
        assertEquals("/chatWindow", viewName);

        verify(userService).saveUserInConversation(any(Conversation.class));
        verify(model).addAttribute(eq("conversation"), any(Conversation.class));

    }

}
