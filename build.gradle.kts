import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
    idea
    java
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "guru.qa"
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("com.codeborne:selenide:7.5.1")
    testImplementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("com.github.javafaker:javafaker:1.0.2")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform{
        if (project.hasProperty("includeTags")) {
            includeTags(project.property("includeTags").toString())
        }
    }
}