# CurseGradleUploader

This is a fork of [matthewprenger CurseGradle](https://github.com/matthewprenger/CurseGradle) plugin which adds the ability to upload to other curseforge sites like [dev.bukkit.org](https://dev.bukkit.org)

The plugin is licensed under the [MIT](./LICENSE.txt) license by [matthewprenger](https://github.com/matthewprenger)

All changes to the project are also licensed unter [MIT](./LICENSE.txt).

To use the plugin add this at the start of your build.gradle

```groovy
buildscript {
	repositories {
		maven { url = "https://repo.u-team.info" }
		jcenter()
	}
	dependencies {
		classpath "info.u-team.curse_gradle_uploader:curse_gradle_uploader:1.4.0"
	}
}

apply plugin: "info.u_team.curse_gradle_uploader"

```

Configure the upload url in the options block:

```groovy
	curseforge {
		apiKey = System.getenv("CURSEFORGE_API_KEY") ?: "unset"
		options {
			apiBaseUrl = "https://dev.bukkit.org"
		}
		project {
			id = "70690"
			changelog = "Changelog"
			releaseType = "alpha"
			
			addGameVersion "1.16"

			mainArtifact(jar) {
				displayName = "Main Artifact"
			}
		}
	}
```

The other project setup is the same as matthewprenger CurseGradle plugin without the integration stuff.
So have a look at the [wiki](https://github.com/matthewprenger/CurseGradle/wiki) there.