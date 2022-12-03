import java.io.File
import java.nio.file.Paths

class FileUtil {
    companion object {
        fun getFileRelativeToRoot(filePathRelativeToRoot: String): File {
            val currentPath = Paths.get("").toAbsolutePath().toString()
            return File("$currentPath$filePathRelativeToRoot")
        }
    }
}
