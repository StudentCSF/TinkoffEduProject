import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

@Testcontainers
@ActiveProfiles("test")
public class IntegrationEnvironment {

    public static JdbcDatabaseContainer DB_CONTAINER;

    static {
        DB_CONTAINER = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("scrapper")
                .withUsername("admin")
                .withPassword("admin");
        DB_CONTAINER.start();

        try {
            Connection connection = DriverManager.getConnection(
                    DB_CONTAINER.getJdbcUrl(),
                    DB_CONTAINER.getUsername(),
                    DB_CONTAINER.getPassword()
            );

            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Path path = Paths.get("")
                    .toAbsolutePath()
                    .getParent()
                    .resolve("migrations");

            Liquibase liquibase = new Liquibase(
                    "master.yml",
                    new DirectoryResourceAccessor(path),
                    database
            );
            liquibase.update(new Contexts(),
                    new LabelExpression(),
                    new BufferedWriter(new PrintWriter(System.out)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
