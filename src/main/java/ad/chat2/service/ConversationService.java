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
public class ConversationService {

    private ConversationRepository conversationRepository;
    private UserRepositories userRepositories;
    private MessageRepositories messageRepositories;

    public ConversationService(ConversationRepository conversationRepository, UserRepositories userRepositories,
            MessageRepositories messageRepositories) {
        this.conversationRepository = conversationRepository;
        this.userRepositories = userRepositories;
        this.messageRepositories = messageRepositories;
    }

    public User findUser(String name) {
        Optional<User> userName = userRepositories.findUserByNick(name);
        if (userName.isPresent()) {
            return userName.get();
        } else {
            throw new EntityNotFoundException("Nie znaleziono encji o identyfikatorze" + name);

        }
    }

    public Conversation findConversation(User us1, User us2) {
        Optional<Conversation> conversation = conversationRepository.findConversationByUs1AndUs2(us1, us2);

        if (conversation.isPresent()) {
            return conversation.get();
        } else {
            throw new EntityNotFoundException("Nie znaleziono encji conversation");
        }

    }

    public List<Message> findMessage(Long id) {

        List<Message> messages = messageRepositories.findMessegesByConversationId(id);

        return messages;
    }
}
