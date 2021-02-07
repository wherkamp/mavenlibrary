plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
    `java-library`
    `maven-publish`
    signing
}

group = "me.kingtux"
version = "1.0-SNAPSHOT"
val artifactName = "mavenlibrary"
java {
    withJavadocJar()
    withSourcesJar()
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
}
repositories {
    mavenCentral()
    jcenter()
}
tasks.withType<Test> {
    useJUnitPlatform()
}
dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("org.dom4j:dom4j:2.1.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation("org.junit.platform:junit-platform-surefire-provider:1.3.2")

}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifactId = artifactName
            artifact(tasks["shadowJar"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(artifactName)
            }
        }
    }
    repositories {
        maven {

            val releasesRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            val snapshotsRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials(PasswordCredentials::class)
        }
        mavenLocal()
    }
}


tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}