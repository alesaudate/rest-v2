package app.car.cap08.infrastructure;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Map;

public class FileUtils {


    @SneakyThrows
    public static String loadFileContents(String fileName) {

        InputStream is = new ClassPathResource(fileName).getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        return new String(data);
    }

    public static String loadFileContents(String fileName, Map<String, String> replacements) {
        String fileContents = loadFileContents(fileName);

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            fileContents = fileContents.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return fileContents;
    }
}
