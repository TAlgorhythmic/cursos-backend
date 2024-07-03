package us.cursos.api;

import us.cursos.User;
import us.cursos.api.utils.DatabaseManager;

import java.util.logging.Logger;

public class Cursos {

    private static Cursos instance;
    private final Logger logger;

    public static void start() {
        if (instance != null) throw new UnsupportedOperationException("Only 1 instance allowed!");
        instance = new Cursos();
    }

    public Cursos() {
        this.logger = Logger.getLogger("Cursos");
    }

    public Logger getLogger() {
        return logger;
    }

    public User getUser(String email) {

    }

    public DatabaseManager getDatabaseManager() {

    }

    public void shutdown(int status) {
        System.exit(status);
    }
}
