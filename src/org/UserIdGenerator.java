package src.org;

import src.org.models.User;
import src.org.repository.UserRepository;

import java.util.Collection;

public class UserIdGenerator {

    private final UserRepository userRepository;

    public UserIdGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getId() {
        long idMax = 0;
        Collection<User> users = userRepository.getAll();

        for (User user : users) {
            if (idMax == 0 || user.getId() > idMax) {
                idMax = user.getId();
            }
        }
        return ++idMax;
    }
}
