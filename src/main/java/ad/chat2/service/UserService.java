package ad.chat2.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ad.chat2.model.Conversation;
import ad.chat2.model.User;
import ad.chat2.repositories.ConversationRepository;
import ad.chat2.repositories.UserRepositories;

@Service
public class UserService {

    UserRepositories userRepositories;
    ConversationRepository conversationRepository;

    public UserService(UserRepositories userRepositories, ConversationRepository conversationRepository) {
        this.userRepositories = userRepositories;
        this.conversationRepository = conversationRepository;
    }

    public void saveUser(User user) {
        userRepositories.save(user);

    }

    public User findUserByName(String name) {
        Optional<User> use = userRepositories.findUserByNick(name);
        if (use.isPresent()) {
            return use.get();
        }
        return null;
    }

    public void saveUserInConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }
}
