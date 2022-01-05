package com.baeldung.dddhexagonalspring.infrastracture.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.UUID;

@SpringJUnitConfig
@SpringBootTest
@TestPropertySource("classpath:ddd-layers-test.properties")
@Testcontainers
public class BaseLiveTest {

    @Container
    static final CassandraContainer cassandraContainer = (CassandraContainer) new CassandraContainer(DockerImageName.parse("cassandra:3.11.5"))
            .withInitScript("cassandra-init.cql")
            .withReuse(true)
            .withLabel("reuse", UUID.nameUUIDFromBytes("dddhexagonalspring-cassandra".getBytes()).toString());

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:3.4.13"))
            .withEnv("MONGO_INITDB_ROOT_USERNAME", "order")
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", "order")
            .withEnv("MONGO_INITDB_DATABASE", "order-database")
            .withClasspathResourceMapping("mongo-init.js",
                    "/docker-entrypoint-initdb.d/mongo-init.js", BindMode.READ_ONLY)
            .withReuse(true)
            .withLabel("reuse", UUID.nameUUIDFromBytes("dddhexagonalspring-mongo".getBytes()).toString());

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.cassandra.local-datacenter", () -> "datacenter1");
        registry.add("spring.data.cassandra.contact-points", cassandraContainer::getContainerIpAddress);
        registry.add("spring.data.cassandra.port", cassandraContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.host", () -> "localhost");
        registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort);
    }
}
