plugins {
    application

    id("org.springframework.boot") version "2.6.2"
    id("com.adarshr.test-logger") version "3.1.0"
}

apply(plugin = "io.spring.dependency-management")

description = "DDD series examples"
group = "com.baeldung"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("com.baeldung.dddhexagonalspring.DomainLayerApplication")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-validation")
    implementation("org.springframework.boot", "spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot", "spring-boot-starter-data-cassandra")

    testImplementation("org.springframework.boot", "spring-boot-starter-test")

    testImplementation(platform("org.testcontainers:testcontainers-bom:1.16.2"))
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mongodb")
    testImplementation("org.testcontainers:cassandra")
}

tasks.test {
    useJUnitPlatform()
}
