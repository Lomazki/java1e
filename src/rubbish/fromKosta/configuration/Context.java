package rubbish.fromKosta.configuration;

import rubbish.fromKosta.component.FileWorker;
import rubbish.fromKosta.model.User;
import rubbish.fromKosta.model.UsersRepository;
import rubbish.fromKosta.validation.RoleValidator;
import rubbish.fromKosta.validation.UserValidator;

import java.util.List;

public class Context {

  private static final String PATH = "Some path";

  private UsersRepository usersRepository;
  private RoleValidator roleValidator;
  private UserValidator userValidator;

  public UsersRepository getUsersRepository() {
    return usersRepository;
  }

  public RoleValidator getRoleValidator() {
    return roleValidator;
  }

  public UserValidator getUserValidator() {
    return userValidator;
  }

  public Context() {
    this.usersRepository = new UsersRepository();
    this.roleValidator = new RoleValidator();
    this.userValidator = new UserValidator();
    loadUsersFromFile(PATH);
  }

  private void loadUsersFromFile(String path) {
    List<User> users = FileWorker.getUsers(path);

    if (users != null && users.size() != 0) {
      for (User user : users) {
        if (user != null && user.getEmail() != null) {
          usersRepository.put(user.getEmail(), user);
        }
      }
    }
  }
}
