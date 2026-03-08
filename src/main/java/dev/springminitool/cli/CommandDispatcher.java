package dev.springminitool.cli;

import dev.springminitool.command.AddControllerCommand;
import dev.springminitool.command.AddEntityCommand;
import dev.springminitool.command.AddServiceCommand;
import dev.springminitool.command.InitCommand;

/**
 * Reads the first CLI argument and delegates to the matching command.
 * Adding a new command only requires registering it here.
 */
public class CommandDispatcher {

    public int dispatch(String[] args) {
        String command = args[0].toLowerCase();

        switch (command) {
            case "init":
                return new InitCommand().execute(args);

            case "add-controller":
                return new AddControllerCommand().execute(args);

            case "add-service":
                return new AddServiceCommand().execute(args);

            case "add-entity":
                return new AddEntityCommand().execute(args);

            default:
                System.err.println("Unknown command: " + args[0]);
                System.err.println("Run without arguments to see available commands.");
                return 1;
        }
    }
}
