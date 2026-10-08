package io.jakeeviado.healthch;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * Provides a real, ephemeral PostgreSQL instance for integration tests via Testcontainers, wired up
 * automatically through Spring Boot's service-connection support (no manual datasource properties
 * needed).
 */
@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

  @Bean
  @ServiceConnection(name = "postgres")
  PostgreSQLContainer postgresContainer() {
    return new PostgreSQLContainer("postgres:16-alpine");
  }
}
