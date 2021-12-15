package FromKosta.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {

  USER("USER", 1),
  CUSTOMER("CUSTOMER", 1),
  ADMIN("ADMIN", 2),
  PROVIDER("PROVIDER", 2),
  SUPER_ADMIN("SUPER_ADMIN", 3);

  public final String name;
  public final int level;

  Role(String name, int level) {
    this.name = name;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  public static String getRolesAsString() {
    return Arrays.toString(Role.values());
  }

  public static List<Role> getRoles() {
    return Arrays.asList(Role.values());
  }

  public static List<String> getRoleNames() {
    return Arrays.stream(Role.values())
        .map(Role::getName)
        .collect(Collectors.toList());
  }
}
