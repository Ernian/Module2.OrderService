import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    java
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "Project.Module2"

abstract class GenerateBuildInfoTask : DefaultTask() {

    @get:Input
    abstract val projectVersion: Property<String>

    @get:Input
    abstract val projectName: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Input
    abstract val dateFormat: Property<String>

    @TaskAction
    fun generate() {
        val formatter = DateTimeFormatter.ofPattern(dateFormat.get())
        val now = LocalDateTime.now().format(formatter)

        val file = outputFile.get().asFile
        file.parentFile.mkdirs()

        file.writeText(
            """
            Version: ${projectVersion.get()}
            Build date: $now
            Project: ${projectName.get()}
            """.trimIndent() + System.lineSeparator()
        )

        logger.lifecycle("Generated ${file.absolutePath}")
    }
}

val generateBuildInfo by tasks.registering(GenerateBuildInfoTask::class) {
    projectVersion.set(project.version.toString())
    projectName.set(project.name)
    outputFile.set(layout.projectDirectory.file("build-info.txt"))
    dateFormat.set("yyyy-MM-dd HH:mm")
    outputs.upToDateWhen { false }
}
