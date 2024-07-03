package us.cursos.api.utils;

public class Config {

    private String httpHost;
    private int httpPort;

    private String databaseHost;
    private String databaseName;
    private String databaseUsername;
    private int databasePort;
    private String databasePassword;

    Config(String httpHost, int httpPort, String databaseHost, String databaseName, String databaseUsername, int databasePort, String databasePassword) {
        this.httpHost = httpHost;
        this.httpPort = httpPort;
        this.databaseHost = databaseHost;
        this.databaseName = databaseName;
        this.databaseUsername = databaseUsername;
        this.databasePort = databasePort;
        this.databasePassword = databasePassword;
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
