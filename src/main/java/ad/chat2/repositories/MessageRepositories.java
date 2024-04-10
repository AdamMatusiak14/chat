package ad.chat2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.chat2.model.Message;
import ad.chat2.model.User;

@Repository
public interface MessageRepositories extends JpaRepository<Message, Long> {

}
