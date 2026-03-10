package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Generates a minimal JPA entity class.
 *
 * The generated entity includes an {@code @Id} field and basic annotations
 * as a starting point. The class name matches the provided name directly
 * (e.g. "Product" -> "Product.java").
 */
public class EntityGenerator {

    private final FileWriter fileWriter;

    public EntityGenerator(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    /**
     * Generates an entity file in the given base directory.
     *
     * @param baseDir     project root directory
     * @param name        entity class name (e.g. "Product")
     * @param packageName Java package name (e.g. "com.example.myapp")
     * @return true if the file was created, false if it already existed
     * @throws IOException if the file could not be written
     */
    public boolean generate(Path baseDir, String name, String packageName) throws IOException {
        String className = capitalize(name);
        String fileName = className + ".java";
        Path modelDir = baseDir.resolve(
            "src/main/java/" + packageName.replace('.', '/') + "/model"
        );
        Path targetFile = modelDir.resolve(fileName);

        String content = buildEntityClass(packageName, name);
        return fileWriter.writeFile(targetFile, content);
    }

    private String buildEntityClass(String packageName, String name) {
        String className = capitalize(name);

        return "package " + packageName + ".model;\n\n"
            + "import jakarta.persistence.Entity;\n"
            + "import jakarta.persistence.GeneratedValue;\n"
            + "import jakarta.persistence.GenerationType;\n"
            + "import jakarta.persistence.Id;\n\n"
            + "@Entity\n"
            + "public class " + className + " {\n\n"
            + "    @Id\n"
            + "    @GeneratedValue(strategy = GenerationType.IDENTITY)\n"
            + "    private Long id;\n\n"
            + "    // TODO: add fields, getters, setters\n\n"
            + "    public Long getId() {\n"
            + "        return id;\n"
            + "    }\n\n"
            + "    public void setId(Long id) {\n"
            + "        this.id = id;\n"
            + "    }\n"
            + "}\n";
    }

    private String capitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
