package info.u_team.curse_gradle_uploader

/**
 * Various options for CurseGradle. These affect the entire plugin and not just a single curse project.
 */
class Options {
	
	/**
	 * Debug mode will stop just short of actually uploading the file to Curse, and instead spit out the JSON
	 * to the console. Useful for testing your buildscript.
	 */
	boolean debug = false
}
