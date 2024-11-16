plugins {
    id("java")
}

group = "ru.seranpol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.guava:guava:33.3.1-jre")
}


tasks.test {
    useJUnitPlatform()
}