plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.devvillar.dateformatutils"
    compileSdk = 34

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    defaultConfig {
        minSdk = 27

        aarMetadata {
            minCompileSdk = 27
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

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

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {
    publications {
        create<MavenPublication>("release") {

            groupId = "io.github.devvillar"
            artifactId = "dateutils"
            version = "1.0.1"

            artifact("${layout.buildDirectory.get().asFile}/outputs/aar/DateFormatUtils-release.aar")

            pom {
                name = "dateutils"
                description = "DateUtils: Convertir fecha a un formato amigable"
                url = "https://github.com/devVillar/demoLibrary.git"
            }
//            pom {
//                packaging = "aar"
//                name = "dateutils"
//                description = "DateUtils: Convertir fecha a un formato amigable"
//                url = "https://github.com/devVillar/demoLibrary.git"
//
//                licenses {
//                    license {
//                        name = "The Apache License, Version 2.0"
//                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
//                    }
//                }
//
//                developers {
//                    developer {
//                        id = "IDDevVillar"
//                        name = "Misael Villar"
//                        email = "devvillar@gmail.com"
//                    }
//                }
//
//                scm {
//                    connection = "scm:git:git://github.com/devVillar/demoLibrary.git"
//                    developerConnection = "scm:git:ssh://github.com/devVillar/demoLibrary.git"
//                    url = "https://github.com/devVillar/demoLibrary"
//                }
//            }
        }
    }

    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/devVillar/demoLibrary")
            credentials {
                username = project.properties["gpr.user"].toString()
                password = project.properties["gpr.key"].toString()
            }
        }

//        maven {
//            name = "sonatype"
//            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
//            credentials {
//                username = "pRI1Zj0/"
//                password = "8204lIRb3djSEePZTdJYZzzwWi9RNo6BlI/zM+/PJ319"
//            }
//        }
    }
}