package dev.springminitool.command;

/**
 * Common interface for all CLI commands.
 *
 * Each command receives the full args array (including the command name at index 0)
 * and returns an exit code: 0 for success, non-zero for errors.
 */
public interface Command {

    int execute(String[] args);
}
