package rubbish.fromKosta.service;

import rubbish.fromKosta.model.User;
import rubbish.fromKosta.model.UsersRepository;
import rubbish.fromKosta.model.ValidationError;
import rubbish.fromKosta.validation.RoleValidator;
import rubbish.fromKosta.validation.UserValidator;

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
