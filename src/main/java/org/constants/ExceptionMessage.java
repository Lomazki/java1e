package org.constants;

public class ExceptionMessage {

    private ExceptionMessage() {
    }

    public static final String PHONE_INCORRECT = "The phone: '%s' number is incorrect. " +
            "Only numbers and one space are allowed. For example, 37576 9851569";
    public static final String INCORRECT_PHONE_NUMBER_COUNT = "Only '%s' phones are allowed";
    public static final String ROLES_ABSENT = "There is no role '%s'. "
            + "Choice a role from list: '%s' ";
    public static final String SUPER_ADMIN_IS_NOT_SINGLE = "If the user has a role specified SUPER_ADMIN, " +
            "then it is forbidden to select other roles";
    public static final String DOUBLE_LEVEL_ROLE = "A role with this level: '%s' already exists";
    public static final String EMAIL_INCORRECT = "The email address '%s' is incorrect. " +
            "The name of the email must contain '@' and '.'";
    public static final String EMAIL_DUPLICATE = "The email '%s' already exists";
    public static final String NAME_INCORRECT = "Input '%s' is incorrect. " +
            "Only letters of the Latin alphabet are allowed";
    public static final String ROLES_LIST_IS_NULL = "List of roles is empty or null";
    public static final String USER_LIST_IS_NULL = "List of users is empty";
    public static final String PHONE_LIST_IS_NULL = "List of phone numbers is empty or null";
    public static final String EMAIL_IS_NULL = "Email is null";
    public static final String NAME_IS_NULL = "Name or lastName is null";

}