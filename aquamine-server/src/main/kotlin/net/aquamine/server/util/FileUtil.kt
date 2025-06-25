package net.aquamine.server.util

import org.kryptonmc.serialization.DataResult
import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

object FileUtil {

    private val STRICT_PATH_SEGMENT_CHECK = Pattern.compile("[-._a-z0-9]+")

    @JvmStatic
    fun decomposePath(path: String): DataResult<List<String>> {
        var slash = path.indexOf('/')
        if (slash == -1) {
            return when (path) {
                "", ".", ".." -> DataResult.error("Invalid path $path!")
                else -> if (!isValidStrictPathSegment(path)) DataResult.error("Invalid path $path!") else DataResult.success(ImmutableLists.of(path))
            }
        }

        val result = ArrayList<String>()
        var cursor = 0
        var finished = false

        while (true) {
            val segment = path.substring(cursor, slash)
            if (segment == "" || segment == "." || segment == "..") return DataResult.error("Invalid segment $segment in path $path!")
            if (!isValidStrictPathSegment(segment)) return DataResult.error("Invalid segment $segment in path $path!")

            result.add(segment)
            if (finished) return DataResult.success(result)
            cursor = slash + 1
            slash = path.indexOf('/', cursor)

            if (slash == -1) {
                slash = path.length
                finished = true
            }
        }
    }

    @JvmStatic
    fun resolvePath(root: Path, segments: List<String>): Path = when (val size = segments.size) {
        0 -> root
        1 -> root.resolve(segments.get(0))
        else -> {
            val path = arrayOfNulls<String>(size - 1)
            for (i in 1 until size) {
                path[i - 1] = segments.get(i)
            }
            root.resolve(NoSpread.fileSystemGetPath(root.fileSystem, segments.get(0), path))
        }
    }

    @JvmStatic
    fun isValidStrictPathSegment(segment: String): Boolean = STRICT_PATH_SEGMENT_CHECK.matcher(segment).matches()

    @JvmStatic
    fun validatePath(path: Array<out String>) {
        require(path.isNotEmpty()) { "Path must have at least one element!" }
        path.forEach {
            require(it != ".." && it != "." && isValidStrictPathSegment(it)) { "Illegal segment $it in path ${path.contentToString()}!" }
        }
    }

    @JvmStatic
    fun createDirectoriesSafe(path: Path) {
        val safePath = if (Files.exists(path)) path.toRealPath() else path
        Files.createDirectories(safePath)
    }
}
