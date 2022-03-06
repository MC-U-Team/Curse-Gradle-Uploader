package info.u_team.curse_gradle_uploader.jsonresponse

import groovy.transform.CompileStatic

/**
 * A dependency
 */
@CompileStatic
class VersionType {
	
	/**
	 * The unique ID
	 */
	int id
	
	/**
	 * The user friendly name
	 */
	String name
	
	/**
	 * The unique slug
	 */
	String slug
	
	@Override
	String toString() {
		return "VersionType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", slug='" + slug + '\'' +
				'}'
	}
}
