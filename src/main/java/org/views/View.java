package org.views;

public interface View {

    void show(Object object);

    void showOrDefault(Object object, String defaultMessage);

}
