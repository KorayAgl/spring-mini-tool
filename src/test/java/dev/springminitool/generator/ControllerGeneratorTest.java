package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControllerGeneratorTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldCreateControllerFile() throws IOException {
        ControllerGenerator generator = new ControllerGenerator(new FileWriter());

        boolean created = generator.generate(tempDir, "Product", "com.example.myapp");

        assertTrue(created, "File should have been created");

        Path expectedFile = tempDir.resolve(
            "src/main/java/com/example/myapp/controller/ProductController.java"
        );
        assertTrue(Files.exists(expectedFile), "Controller file should exist");
    }

    @Test
    void shouldNotOverwriteExistingFile() throws IOException {
        ControllerGenerator generator = new ControllerGenerator(new FileWriter());

        generator.generate(tempDir, "Product", "com.example.myapp");
        boolean secondCall = generator.generate(tempDir, "Product", "com.example.myapp");

        assertFalse(secondCall, "Should not overwrite existing file");
    }

    @Test
    void generatedFileShouldContainClassName() throws IOException {
        ControllerGenerator generator = new ControllerGenerator(new FileWriter());
        generator.generate(tempDir, "Order", "com.example.myapp");

        Path file = tempDir.resolve(
            "src/main/java/com/example/myapp/controller/OrderController.java"
        );
        String content = Files.readString(file);

        assertTrue(content.contains("class OrderController"), "Should contain class name");
        assertTrue(content.contains("@RestController"), "Should contain @RestController");
        assertTrue(content.contains("@RequestMapping"), "Should contain @RequestMapping");
    }
}
