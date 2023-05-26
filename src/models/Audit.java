package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Audit {
    private static Audit instance = null;
    private final FileWriter auditFile;

    private Audit() throws IOException {
        File f = new File("audit.csv");
        f.createNewFile();
        this.auditFile = new FileWriter(f, true);
    }

    public static Audit getInstance() throws IOException {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    // logs a user-initiated command
    public void logCommand(String command) {
        try {
            auditFile.write(command + "," + new Date() + "\n");
        } catch (IOException e) {
            System.out.println("Could not write to audit file: " + e.getMessage());
        }
    }

    // closes the file
    public void close() throws IOException {
        auditFile.close();
    }
}