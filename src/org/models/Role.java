package src.org.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {

    USER("USER", 1),
    CUSTOMER("CUSTOMER", 1),
    ADMIN("ADMIN", 2),
    PROVIDER("PROVIDER", 2),
    SUPER_ADMIN("SUPER_ADMIN", 3);

    private String name;
    private int level;

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

    public static List<String> getRoleNames() {
        return Arrays.stream(Role.values())
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
