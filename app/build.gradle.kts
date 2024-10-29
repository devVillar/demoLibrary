plugins {
    //alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.devvillar.dateutils"

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    compileSdk = 34

    defaultConfig {

        minSdk = 27
        lint.targetSdk = 34
        //versionCode = 1
        //versionName = "1.0"

        aarMetadata {
            minCompileSdk = 27
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    productFlavors {
//        register("foo") {
//
//            aarMetadata {
//                minCompileSdk = 27
//            }
//        }
//        register("bar") {
//            aarMetadata {
//                minCompileSdk = 27
//            }
//        }
//
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


publishing {
    publications {
        create<MavenPublication>("release") {

            groupId = "io.github.devvillar"
            artifactId = "dateutils"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }

            pom {
                packaging = "aar"
                name = "dateutils"
                description = "DateUtils: Convertir fecha a un formato amigable"
                url = "https://github.com/devVillar/demoLibrary.git"

                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }

                developers {
                    developer {
                        id = "IDDevVillar"
                        name = "Misael Villar"
                        email = "devvillar@gmail.com"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/devVillar/demoLibrary.git"
                    developerConnection = "scm:git:ssh://github.com/devVillar/demoLibrary.git"
                    url = "https://github.com/devVillar/demoLibrary"
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = (project.findProperty("ossrhUsername") ?: "").toString()
                password = (project.findProperty("ossrhPassword") ?: "").toString()
            }
        }
    }
}

dependencies {

}
