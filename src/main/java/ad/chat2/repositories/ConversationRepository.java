package ad.chat2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ad.chat2.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
