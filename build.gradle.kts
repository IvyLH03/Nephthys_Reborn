plugins {
    id("java-library")
    kotlin("jvm") version "1.5.30"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    api("net.mamoe", "mirai-core", "2.14.0")
}

