package rubbish.exemple;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Test {
    public static void main(String[] args) throws IOException {

        File File = Path.of("resources", "test.txt").toFile();
        try (FileInputStream inputStream = new FileInputStream(File)){
            byte[]bytes = inputStream.readAllBytes();
            String stringValue = new String(bytes);
            System.out.println(stringValue);
        }

    }
}
