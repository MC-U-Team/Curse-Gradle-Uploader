package info.u_team.curse_gradle_uploader

class CurseExtension {
	
	/**
	 * Default api base url. Will be applied to all projects that don't declare one
	 */
	def apiBaseUrl = 'https://minecraft.curseforge.com'
	
	/**
	 * Optional global apiKey. Will be applied to all projects that don't declare one
	 */
	def apiKey = '' // Initialize to empty string to delay error until the task is actually ran
	
	final Collection<CurseProject> curseProjects = new ArrayList<>()
	
	Options curseGradleOptions = new Options()
	
	/**
	 * Define a new CurseForge project for deployment
	 *
	 * @param configClosure The configuration closure
	 */
	void project(@DelegatesTo(CurseProject) Closure<?> configClosure) {
		CurseProject curseProject = new CurseProject()
		curseProject.with(configClosure)
		if (curseProject.apiBaseUrl == null) {
			curseProject.apiBaseUrl = this.apiBaseUrl
		}
		if (curseProject.apiKey == null) {
			curseProject.apiKey = this.apiKey
		}
		curseProjects.add(curseProject)
	}
	
	void options(Closure<?> configClosure) {
		curseGradleOptions.with(configClosure)
	}
}
