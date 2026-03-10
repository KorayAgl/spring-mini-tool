package dev.springminitool.command;

import dev.springminitool.generator.ProjectScaffoldGenerator;
import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles the `init` command.
 *
 * Creates a minimal Spring Boot project scaffold in the current working directory.
 * Safe to run: existing files are never overwritten.
 */
public class InitCommand implements Command {

    @Override
    public int execute(String[] args) {
        Path targetDir = Paths.get(System.getProperty("user.dir"));

        System.out.println("Initializing Spring Boot project scaffold in:");
        System.out.println("  " + targetDir);
        System.out.println();

        ProjectScaffoldGenerator generator = new ProjectScaffoldGenerator(new FileWriter());

        try {
            generator.generate(targetDir);
            System.out.println();
            System.out.println("Done! Project scaffold created.");
            System.out.println("Next steps:");
            System.out.println("  1. Open the project in your IDE");
            System.out.println("  2. Adjust the groupId and artifactId in pom.xml");
            System.out.println("  3. Run: mvn spring-boot:run");
            return 0;
        } catch (IOException e) {
            System.err.println("Error creating project scaffold: " + e.getMessage());
            return 1;
        }
    }
}
