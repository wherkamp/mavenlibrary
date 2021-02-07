plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "me.kingtux"
version = "1.0-SNAPSHOT"
java {
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11

}
repositories {
    mavenCentral()
    maven("https://repo.ryandw11.com/repository/maven-releases")
    maven("https://repo.potatocorp.dev/storages/maven/tuxjsql")
    jcenter()
}

dependencies {
    implementation(group = "me.kingtux", name = "tuxorm", version = "1.5-SNAPSHOT")
    implementation(group = "me.kingtux", name = "tuxjsql", version = "2.2.0-SNAPSHOT")
    implementation(group = "io.javalin", name = "javalin", version = "3.13.0")
}
