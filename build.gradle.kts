plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    //implementation("com.diffplug.durian:durian:2.0") // one-line lambda exception handling
    //implementation("com.google.apis:google-api-services-books:v1-rev20201021-1.30.10")
    //implementation("com.omertron:API-OMDB:1.5")
    
    //runtimeOnly("org.slf4j:slf4j-log4j12:1.7.30")
    
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

application {
    // Define the main class for the application.
    mainClassName = "it.unibo.oop20.catcombs.game.Catcombs"
}

tasks.register("listPlugins") {
    doLast {
        project.plugins.forEach {
            println(it)
        }
    }
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "it.unibo.oop20.catcombs.game.Catcombs"
    }
}

task("runMain", JavaExec::class) {
    main = "it.unibo.oop20.catcombs.game.Catcombs"
    classpath = sourceSets["main"].runtimeClasspath
}

