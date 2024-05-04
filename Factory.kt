interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

data class PropertyImpl(
    override val name: String,
    override val value: Any
): Property

data class ServerConfigurationImpl(
    override val properties: List<Property>
): ServerConfiguration

fun property(prop: String): Property {
    val (name, value) = prop.split(":")
    return when (name) {
        "port" -> PropertyImpl(name, value.trim().toInt())
        "environment" -> PropertyImpl(name, value.trim())
        else -> throw RuntimeException("Unknown property: $name")
    }
}

val portProperty = property("port: 8080")
val environment = property("environment: production")

fun server(propertyStrings: List<String>): ServerConfiguration {
    val parsedProperties =
        mutableListOf<Property>()
    for (p in propertyStrings) {
        parsedProperties += property(p)
    }
    return ServerConfigurationImpl(parsedProperties)
}

fun main() {
    println(portProperty)
    println(environment)
    println(server(listOf("port: 8080", "environment: production")))
}