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
    jcenter()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("org.dom4j:dom4j:2.1.3")
}
