package io.jakeeviado.healthch;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/** Class responsible for reports database connectivity for the {@code /actuator/health} endpoint. */
@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    public DatabaseHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** Validates the database connection with a 2-second timeout. Will UP if the connection is valid, DOWN otherwise */
    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            boolean valid = connection.isValid(2);
            if (valid) {
                return Health.up()
                        .withDetail("database", "PostgreSQL")
                        .withDetail("status", "reachable")
                        .build();
            }
            return Health.down()
                    .withDetail("database", "PostgreSQL")
                    .withDetail("status", "unreachable")
                    .build();
        } catch (Exception e) {
            return Health.down(e)
                    .withDetail("database", "PostgreSQL")
                    .build();
        }
    }
}
