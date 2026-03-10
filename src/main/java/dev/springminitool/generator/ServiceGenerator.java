package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Generates a Spring Boot service class annotated with {@code @Service}.
 *
 * The generated class provides a clean starting point for business logic.
 * The class name is derived from the provided name (e.g. "Order" -> "OrderService").
 */
public class ServiceGenerator {

    private final FileWriter fileWriter;

    public ServiceGenerator(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    /**
     * Generates a service file in the given base directory.
     *
     * @param baseDir     project root directory
     * @param name        base name for the service (e.g. "Order")
     * @param packageName Java package name (e.g. "com.example.myapp")
     * @return true if the file was created, false if it already existed
     * @throws IOException if the file could not be written
     */
    public boolean generate(Path baseDir, String name, String packageName) throws IOException {
        String className = capitalize(name) + "Service";
        String fileName = className + ".java";
        Path serviceDir = baseDir.resolve(
            "src/main/java/" + packageName.replace('.', '/') + "/service"
        );
        Path targetFile = serviceDir.resolve(fileName);

        String content = buildServiceClass(packageName, name);
        return fileWriter.writeFile(targetFile, content);
    }

    private String buildServiceClass(String packageName, String name) {
        String className = capitalize(name) + "Service";

        return "package " + packageName + ".service;\n\n"
            + "import org.springframework.stereotype.Service;\n\n"
            + "@Service\n"
            + "public class " + className + " {\n\n"
            + "    // TODO: inject repository and implement business logic\n\n"
            + "    public String getAll() {\n"
            + "        return \"all " + name.toLowerCase() + " items\";\n"
            + "    }\n"
            + "}\n";
    }

    private String capitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
