package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class EntityGeneratorTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldCreateEntityFile() throws IOException {
        EntityGenerator generator = new EntityGenerator(new FileWriter());

        boolean created = generator.generate(tempDir, "Product", "com.example.myapp");

        assertTrue(created, "File should have been created");

        Path expectedFile = tempDir.resolve(
            "src/main/java/com/example/myapp/model/Product.java"
        );
        assertTrue(Files.exists(expectedFile), "Entity file should exist");
    }

    @Test
    void shouldNotOverwriteExistingFile() throws IOException {
        EntityGenerator generator = new EntityGenerator(new FileWriter());

        generator.generate(tempDir, "Product", "com.example.myapp");
        boolean secondCall = generator.generate(tempDir, "Product", "com.example.myapp");

        assertFalse(secondCall, "Should not overwrite existing file");
    }

    @Test
    void generatedFileShouldContainEntityAnnotation() throws IOException {
        EntityGenerator generator = new EntityGenerator(new FileWriter());
        generator.generate(tempDir, "Customer", "com.example.myapp");

        Path file = tempDir.resolve(
            "src/main/java/com/example/myapp/model/Customer.java"
        );
        String content = Files.readString(file);

        assertTrue(content.contains("class Customer"), "Should contain class name");
        assertTrue(content.contains("@Entity"), "Should contain @Entity annotation");
        assertTrue(content.contains("@Id"), "Should contain @Id annotation");
    }
}
