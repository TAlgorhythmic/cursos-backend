package us.cursos.api;

import us.cursos.User;
import us.cursos.api.utils.Config;
import us.cursos.api.utils.DatabaseManager;
import us.cursos.api.utils.IniReader;
import us.cursos.backend.AsyncDispatcher;
import us.cursos.exceptions.InvalidConfigurationException;

import java.io.*;
import java.util.logging.Logger;

public class Cursos {

    private static Cursos instance;

    public static Cursos getInstance() {
        return instance;
    }

    private static final File configFile = new File("." + File.separator + "Config.ini");

    private final Logger logger;
    private Config config;
    private final DatabaseManager dbManager;

    public static void start() {
        if (instance != null) throw new UnsupportedOperationException("Only 1 instance allowed!");
        instance = new Cursos();
    }

    private void saveDefaultIfNotExists() throws IOException {
        if (configFile.createNewFile()) {
            logger.warning("Configuration file not found! Creating a new configuration file with default values...");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Config.ini"))); PrintWriter writer = new PrintWriter(configFile)) {
                reader.lines().forEach(writer::println);
            }
            logger.info("Configuration file created! Edit it on '" + configFile.getAbsolutePath() + "'.");
        }
    }

    public Cursos() {
        this.logger = Logger.getLogger("Cursos");

        this.logger.info("Starting service...");
        try {
            saveDefaultIfNotExists();
            this.config = IniReader.read(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            logger.severe("Failed to load configuration! The service will now stop.");
            shutdown(-1);
        }
        this.dbManager = new DatabaseManager(this.config);
        if (!this.dbManager.initDatabaseSchema()) {
            logger.severe("Something went wrong during database initialization. The service will now stop.");
            shutdown(-1);
        }
        AsyncDispatcher.startListenerThread();
    }

    public Logger getLogger() {
        return logger;
    }

    public User getUser(String email) {
        // TODO
        return null;
    }

    public DatabaseManager getDatabaseManager() {
        return dbManager;
    }

    public void shutdown(int status) {
        System.exit(status);
    }
}
