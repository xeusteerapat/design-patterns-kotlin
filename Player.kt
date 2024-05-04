class Player(val name: String) {
    fun getName() {
        println(name)
    }
}

val player = Player("Xeus")

fun main() {
    println(player.name)
}