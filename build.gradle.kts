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
		config = files("${rootProject.rootDir}/gradle/detekt/detekt.yml")
		parallel = true
		buildUponDefaultConfig = true
		reports {
			html.enabled = true
			xml.enabled = true
			txt.enabled = true
		}
	}
}