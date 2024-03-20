package ad.chat2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ad.chat2.dto.DtoUsers;
import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/chat")
    public String startChat(Model model) {
        DtoUsers userDto = new DtoUsers();
        model.addAttribute("userDto", userDto);
        return "/chat";
    }

    @PostMapping("checkUser")
    public String checkUser(DtoUsers userDto, Model model) {
        User use1 = userService.findUserByName(userDto.getUs1().getNick());
        User use2 = userService.findUserByName(userDto.getUs2().getNick());
        Conversation conversation = new Conversation(use1, use2);
        userService.saveUserInConversation(conversation);

        return "/chatWindow";

    }

}
