package info.u_team.curse_gradle_uploader

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

import com.google.common.primitives.Ints

import groovy.transform.CompileStatic
import info.u_team.curse_gradle_uploader.jsonresponse.*

@CompileStatic
class CurseVersions {
	
	private static final Logger log = Logging.getLogger(CurseVersions)
	
	private final Map<String, Integer> gameVersions = new HashMap<>()
	
	/**
	 * Load the valid game versions from CurseForge
	 * @param api The api object
	 */
	CurseVersions(CurseApi api) {
		log.info 'Initializing CurseForge versions...'
		
		final Set<Integer> validVersionTypes = new HashSet<>()
		
		final String versionTypesJson = Util.httpGet(api.apiKey, api.versionTypesUrl)
		
		final VersionType[] types = Util.gson.fromJson(versionTypesJson, VersionType[].class)
		types.each { type ->
			// Drop addons from valid versions
			if(!type.slug.equals("addons")) {
				validVersionTypes.add(type.id)
			}
		}
		
		final String gameVersionsJson = Util.httpGet(api.apiKey, api.versionUrl)
		
		final GameVersion[] versions = Util.gson.fromJson(gameVersionsJson, GameVersion[].class)
		versions.each { version ->
			if (validVersionTypes.contains(version.gameVersionTypeID)) {
				gameVersions.put(version.name, version.id)
			}
		}
		
		log.info 'CurseForge versions initialized'
	}
	
	int[] resolveGameVersion(final Iterable<Object> objects) {
		final Set<Integer> set = new HashSet<>()
		objects.each { obj ->
			final String version = obj.toString()
			final int id = gameVersions.get(version)
			if (id == 0) {
				throw new IllegalArgumentException("$version is not a valid game version. Valid versions are: ${gameVersions.keySet()}")
			}
			set.add(id)
		}
		
		return Ints.toArray(set)
	}
}
