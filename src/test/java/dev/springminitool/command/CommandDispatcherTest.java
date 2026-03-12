package dev.springminitool.command;

import dev.springminitool.cli.CommandDispatcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandDispatcherTest {

    @Test
    void shouldReturnErrorCodeForUnknownCommand() {
        CommandDispatcher dispatcher = new CommandDispatcher();

        int exitCode = dispatcher.dispatch(new String[]{"unknown-command"});

        assertEquals(1, exitCode, "Unknown commands should return exit code 1");
    }

    @Test
    void shouldReturnErrorCodeWhenNameIsMissingForAddController() {
        CommandDispatcher dispatcher = new CommandDispatcher();

        // No name argument provided
        int exitCode = dispatcher.dispatch(new String[]{"add-controller"});

        assertEquals(1, exitCode, "Missing name argument should return exit code 1");
    }

    @Test
    void shouldReturnErrorCodeWhenNameIsMissingForAddService() {
        CommandDispatcher dispatcher = new CommandDispatcher();

        int exitCode = dispatcher.dispatch(new String[]{"add-service"});

        assertEquals(1, exitCode, "Missing name argument should return exit code 1");
    }

    @Test
    void shouldReturnErrorCodeWhenNameIsMissingForAddEntity() {
        CommandDispatcher dispatcher = new CommandDispatcher();

        int exitCode = dispatcher.dispatch(new String[]{"add-entity"});

        assertEquals(1, exitCode, "Missing name argument should return exit code 1");
    }
}
