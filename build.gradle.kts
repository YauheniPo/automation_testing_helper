plugins {
	kotlin("jvm") version "1.9.0"
	id("io.gitlab.arturbosch.detekt") version Versions.detektGradlePlugin
	application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detektGradlePlugin}")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-rules-libraries:${Versions.detektGradlePlugin}")
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-rules-ruleauthors:${Versions.detektGradlePlugin}")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(8)
}

application {
	mainClass.set("MainKt")
}

allprojects {
	
	detekt {
		source = objects.fileCollection().from(io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_JAVA, io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_JAVA, io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_KOTLIN, io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN)
		buildUponDefaultConfig = true
		baseline = file("${rootProject.rootDir}/gradle/detekt/baseline.xml")
		config = files("${rootProject.rootDir}/gradle/detekt/detekt.yml")
	}
}