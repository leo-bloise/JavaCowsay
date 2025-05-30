package leobloise.cowsay.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class AnimalLoader {
    private final ClassLoader classLoader;
    public AnimalLoader() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }
    public List<String> loadAnimal(String name) {
        String filename = String.format("%s.cow", name);
        try(InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            if (inputStream == null) {
                return loadAnimal("default");
            }
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                return reader.lines().toList();
            }
        } catch (Exception exception) {
            return loadAnimal("default");
        }
    }
}
