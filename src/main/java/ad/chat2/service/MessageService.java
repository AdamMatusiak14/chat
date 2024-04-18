package ad.chat2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ad.chat2.model.Conversation;
import ad.chat2.model.Message;
import ad.chat2.model.User;
import ad.chat2.repositories.ConversationRepository;
import ad.chat2.repositories.MessageRepositories;
import ad.chat2.repositories.UserRepositories;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageService {

    private UserRepositories userRepositories;
    private ConversationRepository conversationRepository;
    private MessageRepositories messageRepositories;

    public MessageService(UserRepositories userRepositories, ConversationRepository conversationRepository,
            MessageRepositories messageRepositories) {
        this.userRepositories = userRepositories;
        this.conversationRepository = conversationRepository;
        this.messageRepositories = messageRepositories;

    }

    public User findSenderByNick(String nick) {
        Optional<User> sender = userRepositories.findUserByNick(nick);
        if (sender.isPresent()) {
            return sender.get();
        } else {
            throw new EntityNotFoundException("Nie znaleziono encji o indentyfikatorze" + nick);
        }
    }

    public Conversation findConversationById(Long id) {
        Optional<Conversation> conversation = conversationRepository.findConversationById(id);
        if (conversation.isPresent()) {
            return conversation.get();
        } else {
            throw new EntityNotFoundException("Nie znaleziono encji o identyfikatorze" + id);
        }
    }

}
