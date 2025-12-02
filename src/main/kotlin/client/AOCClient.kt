package client

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class AOCClient {
	private val client: OkHttpClient = OkHttpClient()
	private val sessionCookie = this.javaClass.getResource("/session_cookie.txt")?.readText()

	fun getInput(dayNumber: Int, delimiter: String = "\n"): List<String> {
		val file = File("${repositoryRoot()}/src/main/kotlin/solution/day${dayNumber}/input/input.txt")
		file.parentFile.mkdirs()
		if (!file.exists()) {
			val request = Request.Builder()
				.url("https://adventofcode.com/2025/day/${dayNumber}/input")
				.addHeader("Cookie", "session=${sessionCookie}")
				.build()
			client.newCall(request).execute().use { response ->
				file.writer().use {
					it.write(response.body!!.string())
				}
			}
		}

		return file.readText().trim().split(delimiter)
	}


	private fun repositoryRoot(): Path {
		return Paths.get("").toAbsolutePath()
	}

}