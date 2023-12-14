import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
	java
	id("org.springframework.boot") version "3.0.8"
	id("io.spring.dependency-management") version "1.1.0"
//	id("org.openapi.generator") version "6.3.0"
}

group = "techno.hub"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	val compileOnly by getting {
		extendsFrom(configurations.getByName("annotationProcessor"))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.slf4j:slf4j-api:2.0.9")

	testImplementation("ch.qos.logback:logback-classic:1.4.11")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.security:spring-security-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.security:spring-security-oauth2-client")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("org.springframework.security:spring-security-config")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
	implementation("org.apache.commons:commons-lang3:3.12.0")

	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

	implementation("io.micrometer:micrometer-tracing-bridge-brave")


	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")


	testImplementation("junit:junit:4.13.1")
	testImplementation("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.rest-assured:rest-assured")
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	testImplementation("org.testcontainers:junit-jupiter:1.19.1")
	testImplementation("io.rest-assured:spring-mock-mvc:5.3.2")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.mysql:mysql-connector-j")


	implementation("commons-io:commons-io:2.6")

//	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

//tasks.test {
//	useJUnitPlatform()
//}

configure<DependencyManagementExtension> {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.3")
		mavenBom("io.micrometer:micrometer-tracing-bom:1.1.3")
	}
}