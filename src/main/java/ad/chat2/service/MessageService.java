package ad.chat2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.repositories.ConversationRepository;
import ad.chat2.repositories.UserRepositories;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageService {

    private UserRepositories userRepositories;
    private ConversationRepository conversationRepository;

    public MessageService(UserRepositories userRepositories, ConversationRepository conversationRepository) {
        this.userRepositories = userRepositories;
        this.conversationRepository = conversationRepository;
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
