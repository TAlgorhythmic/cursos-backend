package us.cursos.api.utils;

import us.cursos.exceptions.InvalidConfigurationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IniReader {

    private static final String SEPARATOR = "=";

    private static final String httpHostKey = "httpHost";
    private static final String httpPortKey = "httpPort";

    private static final String databaseHostKey = "dbHost";
    private static final String databaseKey = "database";
    private static final String usernameKey = "username";
    private static final String passwordKey = "password";
    private static final String dbPortKey = "dbPort";

    public static Config read(File configFile) throws IOException, InvalidConfigurationException {
        String httpHost = null;
        int httpPort = 8080;

        String databaseHost = null;
        String database = null;
        String username = null;
        String password = null;
        int dbPort = 3306;

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(SEPARATOR);
                switch (split[0]) {
                    case httpHostKey -> httpHost = split[1];
                    case httpPortKey -> {
                        if (!split[1].matches("[0-9]+")) throw new InvalidConfigurationException("httpPort field in configuration must be an integer number!");
                        httpPort = Integer.parseInt(split[1]);
                    }
                    case databaseHostKey -> databaseHost = split[1];
                    case databaseKey -> database = split[1];
                    case usernameKey -> username = split[1];
                    case passwordKey -> password = split[1];
                    case dbPortKey -> {
                        if (!split[1].matches("[0-9]+")) throw new InvalidConfigurationException("dbPort field in configuration must be an integer number!");
                        dbPort = Integer.parseInt(split[1]);
                    }
                }
            }
        }
        if (httpHost == null || databaseHost == null || database == null || username == null || password == null) throw new InvalidConfigurationException("At least one configuration key is missing! Deleting the config file and letting it generate again might help.");
        return new Config(httpHost, httpPort, databaseHost, database, username, dbPort, password);
    }
}
