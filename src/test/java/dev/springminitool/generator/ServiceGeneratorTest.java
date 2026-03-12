package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ServiceGeneratorTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldCreateServiceFile() throws IOException {
        ServiceGenerator generator = new ServiceGenerator(new FileWriter());

        boolean created = generator.generate(tempDir, "Order", "com.example.myapp");

        assertTrue(created, "File should have been created");

        Path expectedFile = tempDir.resolve(
            "src/main/java/com/example/myapp/service/OrderService.java"
        );
        assertTrue(Files.exists(expectedFile), "Service file should exist");
    }

    @Test
    void shouldNotOverwriteExistingFile() throws IOException {
        ServiceGenerator generator = new ServiceGenerator(new FileWriter());

        generator.generate(tempDir, "Order", "com.example.myapp");
        boolean secondCall = generator.generate(tempDir, "Order", "com.example.myapp");

        assertFalse(secondCall, "Should not overwrite existing file");
    }

    @Test
    void generatedFileShouldContainServiceAnnotation() throws IOException {
        ServiceGenerator generator = new ServiceGenerator(new FileWriter());
        generator.generate(tempDir, "Payment", "com.example.myapp");

        Path file = tempDir.resolve(
            "src/main/java/com/example/myapp/service/PaymentService.java"
        );
        String content = Files.readString(file);

        assertTrue(content.contains("class PaymentService"), "Should contain class name");
        assertTrue(content.contains("@Service"), "Should contain @Service annotation");
    }
}
