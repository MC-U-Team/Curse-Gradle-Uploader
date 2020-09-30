# CurseGradleUploader

This is a fork of [matthewprenger CurseGradle](https://github.com/matthewprenger/CurseGradle) plugin which adds the ability to upload to other curseforge sites like [dev.bukkit.org](https://dev.bukkit.org)

The plugin is licensed under the [MIT](./LICENSE.txt) license by [matthewprenger](https://github.com/matthewprenger)
All changes to the project are also licensed unter [MIT](./LICENSE.txt).

Configure the upload url in the options block:

```groovy
	curseforge {
		apiKey = System.getenv("CURSEFORGE_API_KEY") ?: "unset"
		options {
			javaVersionAutoDetect = false
			forgeGradleIntegration = false
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

The other project setup is the same as matthewprenger CurseGradle plugin. So have a look at the [wiki](https://github.com/matthewprenger/CurseGradle/wiki) there.