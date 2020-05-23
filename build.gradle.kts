plugins {
    `java-library`
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // aggregate jqwik dependency
    testImplementation("net.jqwik:jqwik:1.2.7")

    // Assertions library
    testImplementation("org.assertj:assertj-core:3.12.2")

    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform() {
        includeEngines(
                "jqwik",
                "junit-jupiter"
        )
    }

    include(
            "**/*Properties.class",
            "**/*Test.class",
            "**/*Tests.class"
    )
}
