plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application.
    application
    jacoco
    pmd
    id("com.github.spotbugs") version "4.7.0"
    checkstyle
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

    compileOnly("com.github.spotbugs:spotbugs-annotations:4.2.2")
    compileOnly("net.jcip:jcip-annotations:1.0")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

}

application {
    // Define the main class for the application.
    mainClassName = "eu.eutampieri.catacombs.game.Catcombs"
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
        attributes["Main-Class"] = "eu.eutampieri.catacombs.game.Catcombs"
    }
}

task("runMain", JavaExec::class) {
    main = "eu.eutampieri.catacombs.game.Catcombs"
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

jacoco {
    toolVersion = "0.8.6"
    reportsDirectory.set(file("$buildDir/jacoco"))
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/jacoco/report.xml")
        csv.isEnabled = false
        html.isEnabled = true
        html.destination = file("$buildDir/jacoco/html")
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }
    }
}

pmd {
    isConsoleOutput = true
    toolVersion = "6.32.0"
    rulesMinimumPriority.set(5)
    ruleSets.clear()
    ruleSetFiles(file("$projectDir/config/pmd/pmd.xml"))
}

tasks.spotbugsMain {
    reports.create("html") {
        isEnabled = true
        setStylesheet("fancy-hist.xsl")
    }
}


pmd {
    isConsoleOutput = true
    toolVersion = "6.32.0"
    rulesMinimumPriority.set(5)
    ruleSets.clear()
    ruleSetFiles(file("$projectDir/config/pmd/pmd.xml"))
}

tasks.spotbugsMain {
    reports.create("html") {
        isEnabled = true
        setStylesheet("fancy-hist.xsl")
    }
}


pmd {
    isConsoleOutput = true
    toolVersion = "6.32.0"
    rulesMinimumPriority.set(5)
    ruleSets.clear()
    ruleSetFiles(file("$projectDir/config/pmd/pmd.xml"))
}

tasks.spotbugsMain {
    reports.create("html") {
        isEnabled = true
        setStylesheet("fancy-hist.xsl")
    }
}

