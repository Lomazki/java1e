package org.components.impl;

import org.components.InteractiveWorker;
import org.models.ValidationError;
import org.views.View;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleWorker implements InteractiveWorker {

    Scanner scanner;
    View view;

    public ConsoleWorker(View view) {
        this.scanner = new Scanner(System.in);
        this.view = view;
    }

    private String getPress(String message) {
        view.show(message);
        return scanner.nextLine().trim();
    }

    private List<String> splitPress(String message, String delimiter) {
        String value = getPress(message);
        return Arrays.asList(value.split(delimiter));
    }

    @Override
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

    @Override
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

    @Override
    public String getChoice(List<String> chooseFrom, String message) {
        while (true){
            String value = getPress(message);
            if (chooseFrom.contains(value)){
                return value;
            }
        }
    }


}
