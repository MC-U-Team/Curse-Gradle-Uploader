package info.u_team.curse_gradle_uploader.jsonresponse

import groovy.transform.CompileStatic

/**
 * Returned as JSON upon a successful file upload
 */
@CompileStatic
class UploadResponse {
	
	/**
	 * The ID of the file that was uploaded
	 */
	int id
}
