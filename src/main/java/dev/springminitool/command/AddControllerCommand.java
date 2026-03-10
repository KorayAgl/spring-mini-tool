package dev.springminitool.command;

import dev.springminitool.generator.ControllerGenerator;
import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles the `add-controller <Name>` command.
 *
 * Generates a REST controller class in the current project directory.
 * Assumes the default package "com.example.myapp" unless the project
 * is extended to read the package from configuration.
 */
public class AddControllerCommand implements Command {

    // Default package used by the `init` scaffold.
    // In a future version this could be read from a config file.
    private static final String DEFAULT_PACKAGE = "com.example.myapp";

    @Override
    public int execute(String[] args) {
        if (args.length < 2 || args[1].isBlank()) {
            System.err.println("Usage: add-controller <Name>");
            System.err.println("Example: add-controller Product");
            return 1;
        }

        String name = args[1];
        Path targetDir = Paths.get(System.getProperty("user.dir"));

        System.out.println("Generating controller: " + name + "Controller");

        ControllerGenerator generator = new ControllerGenerator(new FileWriter());

        try {
            boolean created = generator.generate(targetDir, name, DEFAULT_PACKAGE);
            if (created) {
                System.out.println("Done! Controller created.");
            }
            return 0;
        } catch (IOException e) {
            System.err.println("Error generating controller: " + e.getMessage());
            return 1;
        }
    }
}
