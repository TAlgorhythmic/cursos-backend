package us.cursos.api.utils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import us.cursos.api.Cursos;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class DatabaseManager {

    private final MysqlDataSource dataSource;

    public DatabaseManager(Config config) {
        String host = config.getDatabaseHost();
        String database = config.getDatabaseName();
        String username = config.getDatabaseUsername();
        int port = config.getDatabasePort();
        String password = config.getDatabasePassword();

        // Initialize dataSource, object used to create connections
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setServerName(host);
        dataSource.setPort(port);
        dataSource.setDatabaseName(database);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public boolean initDatabaseSchema() {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("schema.sql"); BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String setup = reader.lines().collect(Collectors.joining());
            String[] queries = setup.split(";");
            for (String query : queries) {
                if (query.isEmpty()) continue;
                try (Connection conn = getConnection()) {
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.execute();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            if (Cursos.getInstance() != null) Cursos.getInstance().getLogger().severe("Failed to initialize database schema!");
            return false;
        }
        return true;
    }
}
