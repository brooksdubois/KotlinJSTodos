plugins {
    kotlin("js") version "2.0.21"
    kotlin("plugin.serialization") version "2.1.10"
}

group = "org.brooksdubois"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
            }
            testTask {
                enabled = false
            }
            webpackTask {
                mainOutputFileName = "KotlinJSTodos.js"
            }
        }
        binaries.executable()
    }
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(npm("axios", "1.7.9"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}