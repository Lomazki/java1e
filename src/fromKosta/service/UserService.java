package fromKosta.service;

import fromKosta.model.User;
import fromKosta.model.UsersRepository;
import fromKosta.model.ValidationError;
import fromKosta.validation.RoleValidator;
import fromKosta.validation.UserValidator;

import java.util.Collection;
import java.util.List;

public class UserService {

  private final UsersRepository usersRepository;
  private final RoleValidator roleValidator;
  private final UserValidator userValidator;

  public UserService(UsersRepository usersRepository,
      RoleValidator roleValidator, UserValidator userValidator) {

    this.usersRepository = usersRepository;
    this.roleValidator = roleValidator;
    this.userValidator = userValidator;
  }

  public User create(String firstName, String lastName, String email,
                     List<String> roles, List<String> phoneNumbers) {

    ValidationError validate = roleValidator.validate(roles);
    if (validate != null) {
      // обработка (выдача сообщения пользователю
    }

    // валидация email и phones

    return null;
  }

  public void saveOrEdit(User user) {
    ValidationError validate = userValidator.validate(user);
    if (validate != null) {
      // handle
    }

    usersRepository.put(user.getEmail(), user);
  }

  public void delete(User user) {
    usersRepository.remove(user.getEmail());
  }

  public User findByEmail(String email) {
    return usersRepository.get(email);
  }

  public Collection<User> findAll() {
    return usersRepository.values();
  }
}
