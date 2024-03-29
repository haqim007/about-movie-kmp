plugins {
    kotlin("multiplatform")
    id("com.android.library")
    //Kotlinx Serialization
    kotlin("plugin.serialization") version "1.8.0"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    //Dependencies versions
    val coroutinesVersion = "1.7.3"
    val ktorVersion = "2.3.4"
    val koinVersion = "3.2.0"
    sourceSets {
        val commonMain by getting{
            dependencies {
                //Common dependencies
                dependencies {
                    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                    implementation("io.ktor:ktor-client-core:$ktorVersion")
                    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                    //Use api so that the android app can use it as well
                    // check version on https://insert-koin.io/docs/reference/koin-mp/kmp/
                    api("io.insert-koin:koin-core:$koinVersion")
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                api("io.insert-koin:koin-android:$koinVersion")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies{
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
            
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "dev.haqim.aboutmovie"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
}