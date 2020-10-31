package app.car.cap05.infrastructure;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

public class FileUtils {

    @SneakyThrows
    public static String loadFileContents(String fileName) {

        InputStream is = new ClassPathResource(fileName).getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);
        return new String(data);
    }
}
