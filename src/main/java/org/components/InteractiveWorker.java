package org.components;

import org.models.ValidationError;

import java.util.List;
import java.util.function.Function;

public interface InteractiveWorker {

    String getChoice(String message,Function<String, ValidationError> validator);

    String getChoice(List<String> chooseFrom, String message);

    List<String> getMultiChoice(String message, Function<List<String>, ValidationError> validator,
                                String delimiter);

}
