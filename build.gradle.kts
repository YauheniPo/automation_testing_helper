plugins {
	kotlin("jvm") version "1.9.0"
	id("io.gitlab.arturbosch.detekt") version Versions.detektGradlePlugin
	id("java")
	id("com.github.johnrengelman.shadow") version Versions.shadow
	id("java-library")
	`maven-publish`
}

publishing {
	publications {
		create<MavenPublication>("mavenKotlin") {
			from(components["java"])
			pom {
				name.set("automation_testing_helper")
				description.set("automation_testing_helper")
			}
		}
	}
}

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