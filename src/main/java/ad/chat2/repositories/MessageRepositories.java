package ad.chat2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.chat2.model.Message;

@Repository
public interface MessageRepositories extends JpaRepository<Message, Long> {

}
