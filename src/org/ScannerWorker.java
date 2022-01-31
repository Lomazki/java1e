package src.org;

import src.org.models.ValidationError;
import src.org.repository.impl.UserRepositoryImpl;
import src.org.service.impl.UserServiceImpl;
import src.org.validation.impl.EmailValidatorImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class ScannerWorker {

    private final Scanner scanner;
    private final ConsoleWorker consoleWorker;

    public ScannerWorker(ConsoleWorker consoleWorker) {
        this.scanner = new Scanner(System.in);
        this.consoleWorker = consoleWorker;
    }

    public String editSelect() {
        System.out.println("What will we edit?");
        System.out.println("press 1 - name");
        System.out.println("press 2 - last name");
        System.out.println("press 3 - email");
        System.out.println("press 4 - role");
        System.out.println("press 5 - phone");
        System.out.println("Please, make your choice");
        return scanner.nextLine().trim();
    }

    public String mainSelect() {
        System.out.println("Hi! What would you like?");
        System.out.println("press '1' - Creat new user ");
        System.out.println("press '2' - Find/watch user ");
        System.out.println("press '3' - Edit user ");
        System.out.println("press '4' - Remove user ");
        System.out.println("press '5' - View all users ");
        System.out.println("Please, make your choice");
        return scanner.nextLine().trim();
    }

    public String typingRequest(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public List<String> typingRequestTroughComma(String message) {
        String item = typingRequest(message);
        Pattern pattern = Pattern.compile(",");
        String[] items = pattern.split(item.toUpperCase());
        return Arrays.asList(items);
    }

    //--------------------------------------------------------------
    public static void main(String[] args) {
        ScannerWorker scannerWorker = new ScannerWorker(new ConsoleWorker());
        EmailValidatorImpl emailValidator = new EmailValidatorImpl(new UserServiceImpl(UserRepositoryImpl.getRepository()));

//        Function<String, ValidationError> function = new Function<String, ValidationError>() {
//            @Override
//            public ValidationError apply(String s) {
//                return emailValidator.validate(s);
//            }
//        };
//        Function<String, ValidationError> function2 = string -> emailValidator.validate(string);
//        Function<String, ValidationError> function3 = emailValidator::validate;

        String press = scannerWorker.correctingPress("Введите email: ", emailValidator::validate);

        System.out.println(press);
    }

    public String correctingPress(String message, Function<String, ValidationError> validator) {
        while (true) {
            String value = press(message);
            ValidationError validationError = validator.apply(value);
            if (validationError == null) {
                return value;
            }
            consoleWorker.showMessage(validationError.getMessage());
        }
    }

    public List<String> correctingListPress(String message, Function<List<String>, ValidationError> validator) {
        while (true) {
            List<String> value = pressThroughComma(message);
            ValidationError validationError = validator.apply(value);
            if (validationError == null) {
                return value;
            }
            consoleWorker.showMessage(validationError.getMessage());
        }
    }

    public String press(String message) {

        consoleWorker.showMessage(message);
        return scanner.nextLine().trim();
    }

    public List<String> pressThroughComma(String message) {
        String items = press(message);
        return Arrays.asList(items.split(","));
    }
}
