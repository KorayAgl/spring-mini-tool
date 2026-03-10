package dev.springminitool.command;

import dev.springminitool.generator.ServiceGenerator;
import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles the `add-service <Name>` command.
 *
 * Generates a Spring service class in the current project directory.
 */
public class AddServiceCommand implements Command {

    private static final String DEFAULT_PACKAGE = "com.example.myapp";

    @Override
    public int execute(String[] args) {
        if (args.length < 2 || args[1].isBlank()) {
            System.err.println("Usage: add-service <Name>");
            System.err.println("Example: add-service Order");
            return 1;
        }

        String name = args[1];
        Path targetDir = Paths.get(System.getProperty("user.dir"));

        System.out.println("Generating service: " + name + "Service");

        ServiceGenerator generator = new ServiceGenerator(new FileWriter());

        try {
            boolean created = generator.generate(targetDir, name, DEFAULT_PACKAGE);
            if (created) {
                System.out.println("Done! Service created.");
            }
            return 0;
        } catch (IOException e) {
            System.err.println("Error generating service: " + e.getMessage());
            return 1;
        }
    }
}
