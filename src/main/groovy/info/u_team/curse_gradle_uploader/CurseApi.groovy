package info.u_team.curse_gradle_uploader

import groovy.transform.CompileStatic

@CompileStatic
class CurseApi {
	
	final String baseApiUrl
	final String versionTypesUrl
	final String versionUrl
	final String uploadUrl
	final String apiKey
	
	CurseApi(String baseApiUrl, String apiKey) {
		this.baseApiUrl = baseApiUrl
		this.apiKey = apiKey
		
		versionTypesUrl = "${baseApiUrl}/api/game/version-types"
		versionUrl = "${baseApiUrl}/api/game/versions"
		uploadUrl = "${baseApiUrl}/api/projects/%s/upload-file"
	}
}
