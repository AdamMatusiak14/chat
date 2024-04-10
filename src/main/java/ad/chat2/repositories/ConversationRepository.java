package ad.chat2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ad.chat2.model.Conversation;
import ad.chat2.model.User;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findConversationById(Long id);

    Optional<Conversation> findConversationByUs1AndUs2(User us1, User us2);

}
