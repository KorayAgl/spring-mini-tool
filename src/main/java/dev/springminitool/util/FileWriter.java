package dev.springminitool.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility for writing files safely.
 * Will never silently overwrite an existing file.
 */
public class FileWriter {

    /**
     * Writes content to the given path.
     * Creates parent directories if they do not exist.
     *
     * @param path    target file path
     * @param content text content to write
     * @return true if the file was written, false if it already existed
     * @throws IOException if the file could not be written
     */
    public boolean writeFile(Path path, String content) throws IOException {
        if (Files.exists(path)) {
            System.out.println("  [skip] already exists: " + path);
            return false;
        }

        Files.createDirectories(path.getParent());
        Files.writeString(path, content);
        System.out.println("  [create] " + path);
        return true;
    }

    /**
     * Creates a directory (and all parent directories) if it does not exist.
     *
     * @param path directory path to create
     * @throws IOException if the directory could not be created
     */
    public void createDirectory(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            System.out.println("  [mkdir]  " + path);
        }
    }
}
