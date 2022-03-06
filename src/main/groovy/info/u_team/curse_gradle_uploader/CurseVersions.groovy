package info.u_team.curse_gradle_uploader

import com.google.common.base.Throwables

import gnu.trove.map.TObjectIntMap
import gnu.trove.map.hash.TObjectIntHashMap
import gnu.trove.set.TIntSet
import gnu.trove.set.hash.TIntHashSet
import info.u_team.curse_gradle_uploader.jsonresponse.*

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class CurseVersions {
	
	private static final Logger log = Logging.getLogger(CurseVersions)
	
	private static final TObjectIntMap<String> gameVersions = new TObjectIntHashMap<>()
	
	/**
	 * Load the valid game versions from CurseForge
	 * @param apiKey The api key to use to connect to CurseForge
	 */
	static void initialize(String apiKey) {
		
		gameVersions.clear()
		
		log.info 'Initializing CurseForge versions...'
		
		try {
			TIntSet validVersionTypes = new TIntHashSet()
			
			String versionTypesJson = Util.httpGet(apiKey, CurseGradlePlugin.getVersionTypesUrl())
			
			VersionType[] types = Util.gson.fromJson(versionTypesJson, VersionType[].class)
			types.each { type ->
				validVersionTypes.add(type.id)
			}
			
			String gameVersionsJson = Util.httpGet(apiKey, CurseGradlePlugin.getVersionUrl())
			
			GameVersion[] versions = Util.gson.fromJson(gameVersionsJson, GameVersion[].class)
			versions.each { version ->
				if (validVersionTypes.contains(version.gameVersionTypeID)) {
					gameVersions.put(version.name, version.id)
				}
			}
			
			log.info 'CurseForge versions initialized'
		} catch (Throwable throwable) {
			throw throwable
		}
	}
	
	static int[] resolveGameVersion(final Iterable<Object> objects) {
		TIntSet set = new TIntHashSet()
		objects.each { obj ->
			final String version = obj.toString()
			int id = gameVersions.get(version)
			if (id == 0) {
				throw new IllegalArgumentException("$version is not a valid game version. Valid versions are: ${gameVersions.keySet()}")
			}
			set.add(id)
		}
		
		return set.toArray()
	}
}
