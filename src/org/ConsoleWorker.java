package src.org;

import src.org.models.ValidationError;
import src.org.validation.EmailValidator;
import src.org.validation.NameValidator;
import src.org.validation.PhoneValidator;
import src.org.validation.RoleValidator;
import src.org.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleWorker {

    Scanner scanner;
    View view;

    public ConsoleWorker(View view) {
        this.scanner = new Scanner(System.in);
        this.view = view;
    }

    public String getPress(String message) {
        view.show(message);
        return scanner.nextLine().trim();
    }

    public List<String> splitPress(String message, String delimiter) {
        String value = getPress(message);
        return Arrays.asList(value.split(delimiter));
    }

    public String getChoice(String message,
                            Function<String, ValidationError> validator) {
        while (true) {
            String value = getPress(message);
            ValidationError error = validator.apply(value);
            if (error == null) {
                return value;
            }
            view.show(error.getMessage());
        }
    }

    public List<String> getMultiChoice(String message, Function<List<String>, ValidationError> validator,
                                       String delimiter) {
        while (true) {
            List<String> value = splitPress(message, delimiter);
            ValidationError error = validator.apply(value);
            if (error == null) {
                return value;
            }
            view.show(error.getMessage());
        }
    }





    // не должен знать о валидаторе. и название должно быть общеею. без неймов юзеров и т.д.
    public String getChoice(List<String> chooseFrom, String message) {
        while (true){
            String value = getPress(message);
            if (chooseFrom.contains(value)){
                return value;
            }
        }
    }
}
