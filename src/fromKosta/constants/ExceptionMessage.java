package fromKosta.constants;

public class ExceptionMessage {

  private ExceptionMessage() {
  }

  public static final String ROLES_ABSENT = "Пользователь должен иметь хотябы одну роль. Выберите "
      + "пожалуйста хотябы одну роль из списка: '%s'";
  public static final String ROLE_IS_INCORRECT = "Некорректная пользовательская роль: '%s'. "
      + "Выберите пожалуйста роли из списка: '%s'";
  public static final String DOUBLE_ROLE = "Одновременно пользователь может иметь по 1 роли с "
      + "каждого уровня. А уровень роли: '%s' задан Вами как минимум два раза";
  public static final String SUPER_ADMIN_IS_NOT_SINGLE = "Если у пользователя указана роль "
      + "SUPER_ADMIN - другие роли выбирать запрещено";
}
