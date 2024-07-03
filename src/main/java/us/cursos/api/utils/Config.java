package us.cursos.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Config {

    private String databaseHost;
    private String databaseName;
    private String databaseUsername;
    private int databasePort;
    private String databasePassword;

    public Config() {}

    public void load(BufferedReader reader) throws IOException {
        try (reader) {

        }
    }

    public int getDatabasePort() {
        return databasePort;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }
}
