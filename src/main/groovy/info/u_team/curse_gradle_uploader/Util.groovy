package info.u_team.curse_gradle_uploader

import com.google.common.base.Charsets
import com.google.common.io.Files
import com.google.gson.Gson

import groovy.transform.CompileStatic
import info.u_team.curse_gradle_uploader.jsonresponse.CurseError

import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.config.CookieSpecs
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.bundling.AbstractArchiveTask

import static com.google.common.base.Preconditions.checkNotNull

@CompileStatic
class Util {
	
	private static final Logger log = Logging.getLogger(Util)
	
	static final Gson gson = new Gson()
	
	/**
	 * Resolve an object into a file
	 *
	 * @param project The project
	 * @param obj The object to resolve
	 * @return A file instance
	 */
	static File resolveFile(Project project, Object obj) {
		if (obj == null) {
			throw new NullPointerException("Null path")
		}
		if (obj instanceof File) {
			return (File) obj
		}
		if (obj instanceof AbstractArchiveTask) {
			return ((AbstractArchiveTask) obj).getArchivePath()
		}
		return project.file(obj)
	}
	
	/**
	 * Resolve an object into a String. If a file is passed, it will be read and it's contents returned
	 *
	 * @param obj The object to resolve
	 * @return A string
	 */
	static String resolveString(Object obj) {
		checkNotNull(obj)
		
		while(obj instanceof Closure) {
			obj = ((Closure)obj).call()
		}
		
		if (obj instanceof String) {
			return (String) obj
		}
		if (obj instanceof File) {
			String data = new String(((File) obj).getText('UTF-8').getBytes('UTF-8'))
			return data;
		}
		
		if (obj instanceof AbstractArchiveTask) {
			String data = new String(((File) ((AbstractArchiveTask) obj).archiveFile.get().asFile).getText('UTF-8').getBytes('UTF-8'))
			return data;
		}
		
		return obj.toString()
	}
	
	/**
	 * Issue an HTTP GET to a CurseForge API URL
	 *
	 * @param apiKey The apiKey to use for connecting
	 * @param url The url
	 * @return The data
	 */
	static String httpGet(String apiKey, String url) {
		log.debug "HTTP GET to URL: $url"
		
		HttpClient client = HttpClientBuilder.create()
				.setDefaultRequestConfig(RequestConfig.custom()
				.setCookieSpec(CookieSpecs.IGNORE_COOKIES).build()).build()
		
		HttpGet get = new HttpGet(new URI(url))
		get.setHeader('X-Api-Token', apiKey)
		
		HttpResponse response = client.execute(get)
		
		int statusCode = response.statusLine.statusCode
		
		if (statusCode == 200) {
			byte[] data = response.entity.content.bytes
			return new String(data, Charsets.UTF_8)
		} else {
			if (response.getFirstHeader('content-type').value.contains('json')) {
				InputStreamReader reader = new InputStreamReader(response.entity.content)
				CurseError error = gson.fromJson(reader, CurseError)
				reader.close()
				throw new RuntimeException("[CurseForge] Error Code ${error.errorCode}: ${error.errorMessage}")
			} else {
				throw new RuntimeException("[CurseForge] HTTP Error Code $response.statusLine.statusCode: $response.statusLine.reasonPhrase")
			}
		}
	}
	
	/**
	 * Check if a condition is true, and raise an exception if not
	 *
	 * @param condition The condition
	 * @param message The message to display
	 */
	static void check(boolean condition, String message) {
		if (!condition) {
			throw new RuntimeException(message)
		}
	}
}
