plugins {
    java
    application
}

application {
    mainClass.set("de.dhbw.turniermanager.infrastructure.cli.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}