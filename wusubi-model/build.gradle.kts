import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies{
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
