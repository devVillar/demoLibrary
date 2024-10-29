plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}


android {
    namespace = "com.devvillar.demolibrary"

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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "io.github.devvillar"
                artifactId = "demolibrary"
                version = "1.0.0"

                pom {
                    packaging = "aar"
                    name = "demolibrary"
                    description = "Demolibrary: Convertir fecha a un formato amigable"
                    url = "URL del proyecto"

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
                        connection = "scm:git:git://github.com/usuario/proyecto.git"
                        developerConnection = "scm:git:ssh://github.com:usuario/proyecto.git"
                        url = "https://github.com/usuario/proyecto"
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
}

dependencies {

}
