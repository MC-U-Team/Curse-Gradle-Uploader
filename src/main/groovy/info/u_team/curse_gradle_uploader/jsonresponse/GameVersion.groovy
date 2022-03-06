package info.u_team.curse_gradle_uploader.jsonresponse

import groovy.transform.CompileStatic

/**
 * A game version
 */
@CompileStatic
class GameVersion {
	
	/**
	 * The unique ID
	 */
	int id
	
	/**
	 * Game dependency ID
	 */
	int gameVersionTypeID
	
	/**
	 * A friendly name
	 */
	String name
	
	/**
	 * The unique slug
	 */
	String slug
	
	@Override
	String toString() {
		return "GameVersion{" +
				"id=" + id +
				", gameVersionTypeID=" + gameVersionTypeID +
				", name='" + name + '\'' +
				", slug='" + slug + '\'' +
				'}'
	}
}
