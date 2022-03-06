package info.u_team.curse_gradle_uploader


class CurseApi {
	
	private String baseApiUrl
	private String versionTypesUrl
	private String versionUrl
	private String uploadUrl
	private String apiKey
	
	CurseApi(String baseApiUrl, String apiKey) {
		this.baseApiUrl = baseApiUrl
		this.apiKey = apiKey
		
		versionTypesUrl = "${baseApiUrl}/api/game/version-types"
		versionUrl = "${baseApiUrl}/api/game/versions"
		uploadUrl = "${baseApiUrl}/api/projects/%s/upload-file"
	}
}
