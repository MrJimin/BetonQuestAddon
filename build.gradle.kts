import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.2.0"
    id("com.gradleup.shadow") version "9.0.0-beta10"
}

group = "com.github.mrjimin"
version = "1.1.0"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    mavenCentral()
    maven("https://repo.nexomc.com/releases")
    maven("https://jitpack.io")
    // maven("https://nexus.betonquest.org/repository/betonquest/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("com.nexomc:nexo:1.8.0")
    compileOnly("com.github.LoneDev6","api-itemsadder","2.1.25")
    compileOnly("su.nightexpress.coinsengine","CoinsEngine","2.5.0")

    implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:10.1.0")

    compileOnly(fileTree("lib") {
        include("*.jar")
    })
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.withType<ShadowJar> {
    exclude("kotlin/**")
    exclude("org/**")

    from("LICENSE")

    relocate("dev.jorel.commandapi", "com.github.mrjimin.betonquestaddon.lib.commandapi")

    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }

    archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
    destinationDirectory=file("C:\\Users\\aa990\\OneDrive\\바탕 화면\\BQ\\plugins")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

kotlin {
    jvmToolchain(21)
}