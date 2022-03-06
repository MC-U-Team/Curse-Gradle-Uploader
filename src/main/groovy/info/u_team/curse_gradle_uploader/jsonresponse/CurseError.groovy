package info.u_team.curse_gradle_uploader.jsonresponse

import groovy.transform.CompileStatic

/**
 * Returned as JSON when an error occurs
 */
@CompileStatic
class CurseError {
	
	/**
	 * The HTTP status code
	 */
	int errorCode
	
	/**
	 * The error message
	 */
	String errorMessage
	
	@Override
	public String toString() {
		return "CurseError{" +
				"errorCode=" + errorCode +
				", errorMessage='" + errorMessage + '\'' +
				'}'
	}
}
