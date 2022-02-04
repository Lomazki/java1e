package src.org.view.impl;

import src.org.view.View;

public class ConsoleView implements View {

    @Override
    public void show(Object object) {
        System.out.println(object);
    }

    @Override
    public void showOrDefault(Object object, String defaultMessage) {
        if (object == null) {
            show(defaultMessage);
        } else {
            show(object);
        }
    }
}
