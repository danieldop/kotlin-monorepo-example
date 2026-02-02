import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlinVersion: String by project
val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

group = "com.acme"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    id("io.quarkus")
    id("org.kordamp.gradle.jandex") version "2.3.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    val jacksonVersion = "3.0.3"

//    quarkus
    api(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
//    kotlin
    api("io.quarkus:quarkus-kotlin")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    rest
    api("io.quarkus:quarkus-rest-jackson")
    api("io.quarkus:quarkus-rest-client-jackson")
    api("io.quarkus:quarkus-hibernate-validator")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonVersion}")
//    persistence
    api("io.quarkus:quarkus-mongodb-panache-kotlin")
//    messaging
    api("io.quarkus:quarkus-messaging-kafka")
//    documentation
    api("io.quarkiverse.asyncapi:quarkus-asyncapi-scanner:1.0.5")
    api("io.quarkus:quarkus-smallrye-openapi")
//    observability
    api("io.quarkus:quarkus-observability-devservices-lgtm")
    api("io.quarkus:quarkus-opentelemetry")
    api("io.quarkus:quarkus-micrometer-opentelemetry")
//    infrastructure
    api("io.quarkus:quarkus-config-yaml")
    api("io.quarkus:quarkus-arc")
//    cloud
//    implementation("io.quarkus:quarkus-kubernetes")
//    api("io.quarkiverse.helm:quarkus-helm")
    implementation("io.quarkus:quarkus-smallrye-health")
//    testing
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}

tasks.quarkusDev {
    compilerOptions {
        compiler("kotlin").args(listOf("-Werror"))
    }
}

tasks.withType<AbstractTestTask>().configureEach {
    failOnNoDiscoveredTests = false
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        javaParameters = true
        freeCompilerArgs.set(
            listOf(
                "-Xannotation-default-target=param-property",
                "-opt-in=kotlin.time.ExperimentalTime",
                "-opt-in=kotlin.uuid.ExperimentalUuidApi"
            )
        )
    }
}
