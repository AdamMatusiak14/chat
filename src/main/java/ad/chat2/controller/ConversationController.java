package ad.chat2.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ad.chat2.dto.DtoConversation;
import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.repositories.ConversationRepository;
import ad.chat2.service.ConversationService;

@Controller
public class ConversationController {

    private ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/findConversation")
    public String conversation(Model model) {
        DtoConversation convertDto = new DtoConversation();
        model.addAttribute("convertDto", convertDto);
        return "findConversation";
    }

    @PostMapping("/findConversation")
    public String findConversation(DtoConversation convertDto, Model model) {
        User user1 = conversationService.findUser(convertDto.getFirstName());

        User user2 = conversationService.findUser(convertDto.getSecondName());

        Conversation conversation = conversationService.findConversation(user1, user2);

        model.addAttribute("conversation", conversation);

        return "/viewConversation";

    }

}
