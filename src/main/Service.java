package main;

import main.database.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class Service {

    public String entered() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Записываем в файл
    void writeInFile(String res) throws IOException {
        Path path = Path.of("resources", "base.txt");
        Files.writeString(path, res);
    }

    // Читаем из файла
    void readFile(String res) throws IOException {
        Path path2 = Path.of("resources", "base.txt");
        try (Stream<String> lines = Files.lines(path2)) {
            lines.forEach(System.out::println);
        }
    }

    public void mainChoice(String entered) {
        switch (entered) {
            case ("1"):
                CreateUser.start();
                break;
            case ("2"):
                FindUser.start();
                break;
            case ("3"):
                EditUser.start();
                break;
            case ("4"):
                RemoveUser.start();
                break;
            case ("5"):
                ShowAllUsers.start();
                break;
        }
    }
}

