package dev.springminitool.command;

import dev.springminitool.generator.EntityGenerator;
import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles the `add-entity <Name>` command.
 *
 * Generates a JPA entity class in the current project directory.
 */
public class AddEntityCommand implements Command {

    private static final String DEFAULT_PACKAGE = "com.example.myapp";

    @Override
    public int execute(String[] args) {
        if (args.length < 2 || args[1].isBlank()) {
            System.err.println("Usage: add-entity <Name>");
            System.err.println("Example: add-entity Product");
            return 1;
        }

        String name = args[1];
        Path targetDir = Paths.get(System.getProperty("user.dir"));

        System.out.println("Generating entity: " + name);

        EntityGenerator generator = new EntityGenerator(new FileWriter());

        try {
            boolean created = generator.generate(targetDir, name, DEFAULT_PACKAGE);
            if (created) {
                System.out.println("Done! Entity created.");
            }
            return 0;
        } catch (IOException e) {
            System.err.println("Error generating entity: " + e.getMessage());
            return 1;
        }
    }
}
