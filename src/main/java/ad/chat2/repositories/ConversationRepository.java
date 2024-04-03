package ad.chat2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ad.chat2.model.Conversation;
import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findConversationById(Long id);

}
