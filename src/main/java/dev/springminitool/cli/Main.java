package dev.springminitool.cli;

/**
 * Entry point for the spring-mini-tool CLI.
 *
 * Usage:
 *   java -jar spring-mini-tool.jar <command> [arguments]
 *
 * Available commands:
 *   init                  - scaffold a new Spring Boot project
 *   add-controller <Name> - generate a REST controller class
 *   add-service <Name>    - generate a service class
 *   add-entity <Name>     - generate a JPA entity class
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        CommandDispatcher dispatcher = new CommandDispatcher();
        int exitCode = dispatcher.dispatch(args);
        System.exit(exitCode);
    }

    private static void printUsage() {
        System.out.println("spring-mini-tool - scaffold Spring Boot structures");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  java -jar spring-mini-tool.jar <command> [arguments]");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  init                   Create a new Spring Boot project scaffold");
        System.out.println("  add-controller <Name>  Generate a REST controller class");
        System.out.println("  add-service <Name>     Generate a service class");
        System.out.println("  add-entity <Name>      Generate a JPA entity class");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java -jar spring-mini-tool.jar init");
        System.out.println("  java -jar spring-mini-tool.jar add-controller Product");
        System.out.println("  java -jar spring-mini-tool.jar add-service Order");
    }
}
