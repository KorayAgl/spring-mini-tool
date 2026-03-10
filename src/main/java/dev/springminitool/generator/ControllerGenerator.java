package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Generates a simple Spring Boot REST controller class.
 *
 * The generated controller includes a basic GET endpoint as a starting point.
 * The class name is derived from the provided name (e.g. "Product" -> "ProductController").
 */
public class ControllerGenerator {

    private final FileWriter fileWriter;

    public ControllerGenerator(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    /**
     * Generates a controller file in the given base directory.
     *
     * @param baseDir     project root directory
     * @param name        base name for the controller (e.g. "Product")
     * @param packageName Java package name (e.g. "com.example.myapp")
     * @return true if the file was created, false if it already existed
     * @throws IOException if the file could not be written
     */
    public boolean generate(Path baseDir, String name, String packageName) throws IOException {
        String className = capitalize(name) + "Controller";
        String fileName = className + ".java";
        Path controllerDir = baseDir.resolve(
            "src/main/java/" + packageName.replace('.', '/') + "/controller"
        );
        Path targetFile = controllerDir.resolve(fileName);

        String content = buildControllerClass(packageName, name);
        return fileWriter.writeFile(targetFile, content);
    }

    private String buildControllerClass(String packageName, String name) {
        String className = capitalize(name) + "Controller";
        String lowerName = name.toLowerCase();

        return "package " + packageName + ".controller;\n\n"
            + "import org.springframework.web.bind.annotation.GetMapping;\n"
            + "import org.springframework.web.bind.annotation.RequestMapping;\n"
            + "import org.springframework.web.bind.annotation.RestController;\n\n"
            + "@RestController\n"
            + "@RequestMapping(\"/" + lowerName + "\")\n"
            + "public class " + className + " {\n\n"
            + "    @GetMapping\n"
            + "    public String getAll() {\n"
            + "        // TODO: implement\n"
            + "        return \"List of " + lowerName + "\";\n"
            + "    }\n"
            + "}\n";
    }

    private String capitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
