package org;

import java.io.*;

public class Runner implements Serializable {

    private static final long serialVersionUID = 1575349246421981036L;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Reception reception = new Reception();
        reception.mainChoice();

    }
}
