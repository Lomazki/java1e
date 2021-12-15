package FromKosta;

import FromKosta.configuration.Context;
import FromKosta.model.ValidationError;
import FromKosta.validation.RoleValidator;

import java.util.Arrays;
import java.util.List;

public class LaunchApplication {

  public static void main(String[] args) {
    launch();
  }

  private static void launch() {
    Context context = new Context();
    for (; ; ) {

    }
  }


  public static void test() {
    String[] roles1 = {"feretrtr", "USER"}; // false
    String[] roles2 = {"USER", "CUSTOMER"}; // false
    String[] roles3 = {"USER", "PROVIDER"}; // true
    String[] roles4 = {"SUPER_ADMIN"}; // true
    String[] roles5 = {"SUPER_ADMIN", "USER"}; // false
    String[] roles6 = {"USER", "SUPER_ADMIN"}; // false
    String[] roles7 = {"PROVIDER", "CUSTOMER"}; // true
    String[] roles8 = {"PROVIDER", "ADMIN"}; // false
    String[] roles9 = {"ADMIN", "PROVIDER"}; // false
    String[] roles10 = {"USER", "PROVIDER", "dfdfdfdf"}; // false
    String[] roles11 = null; // false
    String[] roles12 = {}; // false

    List<String[]> rolesList = Arrays.asList(roles1, roles2, roles3, roles4, roles5, roles6, roles7,
        roles8, roles9, roles10, roles11, roles12);

    RoleValidator roleValidator = new RoleValidator();

    for (String[] roles : rolesList) {
      System.out.print(Arrays.toString(roles) + " - ");
      ValidationError validationError = roleValidator.validate(roles);

      if (validationError == null) {
        System.out.println("true");
      } else {
        System.out.println(validationError.getMessage());
      }
    }
  }
}
