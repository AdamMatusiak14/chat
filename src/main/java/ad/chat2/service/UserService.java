package ad.chat2.service;

import org.springframework.stereotype.Service;

import ad.chat2.model.User;
import ad.chat2.repositories.UserRepositories;

@Service
public class UserService {

    UserRepositories userRepositories;

    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public void saveUser(User user) {
        userRepositories.save(user);

    }

}
