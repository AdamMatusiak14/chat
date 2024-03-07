package ad.chat2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ad.chat2.model.User;

public interface UserRepositories extends JpaRepository<User, Long> {

}
