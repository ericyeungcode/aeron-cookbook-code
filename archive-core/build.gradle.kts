
plugins {
    application
    checkstyle
}

dependencies {
    checkstyle(libs.checkstyle)
    implementation(libs.agrona)
    implementation(libs.aeron.archive)
    implementation(libs.slf4j)
    implementation(libs.logback)
    testImplementation(libs.bundles.testing)
}

testing {
    suites {
        // Configure the built-in test suite
        @Suppress("UNUSED_VARIABLE")
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter(libs.versions.junitVersion.get())

            targets {
                all {
                    testTask {
                        jvmArgs(
                            "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED",
                            "--add-opens", "java.base/java.util.zip=ALL-UNNAMED")
                    }
                }
            }
        }
    }
}


application {
   mainClass.set(project.findProperty("mainClass")?.toString() ?: "com.example.DefaultMain")

   /*
   Fix unsafeApi err:
   Exception in thread "main" java.lang.IllegalAccessError: class org.agrona.UnsafeApi (in unnamed module @0x5ce81285) cannot access class jdk.internal.misc.Unsafe (in module java.base) because module java.base does not export jdk.internal.misc to unnamed module
    */   
   applicationDefaultJvmArgs = listOf(
       "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED",
       "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED",
       "--add-opens", "java.base/java.nio=ALL-UNNAMED",
       "--add-opens", "java.base/sun.misc=ALL-UNNAMED"
   )   
}