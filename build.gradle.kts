import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    jacoco
    id("org.springframework.boot") version "2.6.7" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("java-library")

    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"

    id("com.google.cloud.tools.jib") version "3.1.4"
}

buildscript {
    repositories {
        mavenCentral()
        maven("https://repo.spring.io/plugins-release")
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("org.jmailen.gradle:kotlinter-gradle:3.6.0")
        classpath(kotlin("gradle-plugin", version = "1.6.21"))
        classpath(kotlin("compiler-embeddable", version = "1.6.21"))
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.6.7")
    }
}

idea.project {
    jdkName = "11"
    languageLevel = IdeaLanguageLevel(JavaVersion.VERSION_11)
    kotlin {
        coreLibrariesVersion = "1.6.21"
    }
}

jacoco.toolVersion = "0.8.5"

allprojects {
    group = "com.wusubi.core"
    version = "0.0.1-SNAPSHOT"

    apply {
        plugin("idea")
        plugin("kotlin")
        plugin("org.jmailen.kotlinter")
        plugin("jacoco")

        plugin("kotlin-spring")
        plugin("kotlin-jpa")
        plugin("kotlin-kapt")
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        api(kotlin("stdlib-jdk8", version = "1.6.21"))
        api(kotlin("stdlib-jdk7", version = "1.6.21"))
        api(kotlin("stdlib", version = "1.6.21"))
        api(kotlin("stdlib-common", version = "1.6.21"))
        api(kotlin("reflect", version = "1.6.21"))

        implementation("com.google.guava:guava:28.2-jre")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.5")

        testImplementation(kotlin("test", version = "1.6.21"))
        testImplementation(kotlin("test-junit5", version =  "1.6.21"))
        testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
        testImplementation("io.mockk:mockk:1.10.4")
        testImplementation("com.appmattus.fixture:fixture:1.2.0")
    }
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("com.google.cloud.tools.jib")
    }

    jib {
        to {
            tags = setOf("latest", System.getProperty("IMAGE_TAG", version.toString()))
        }
        // Default container policy
        container {
            format = com.google.cloud.tools.jib.api.buildplan.ImageFormat.OCI
            creationTime = "USE_CURRENT_TIMESTAMP"
        }
    }

    tasks {
        getByName<Jar>("jar") {
            enabled = false
        }
    }
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
