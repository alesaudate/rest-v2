package app.car.cap09.infrastructure

import org.springframework.core.io.ClassPathResource

fun loadFileContents(fileName: String, replacements: Map<String, String> = emptyMap()) : String {
    val fileContents = ClassPathResource(fileName).inputStream.readAllBytes().decodeToString()
    fileContents.apply {
        var contents = this
        replacements.forEach { (key, value) -> contents = contents.replace("{{$key}}", value) }
        return contents
    }
}
