fun main(args: Array<String>) {

    arrays()
    println(args.joinToString(", "))

    val nums = listOf(1,3,45,6,3)

    for (num in nums) {
        println(num)
    }

    nums.forEachIndexed { idx, num ->
        println("index: $idx, num: $num")
    }

    val user = mapOf(
        "id" to 1,
        "name" to "Teerapat"
    )

    println(user)

    val myName = "teerapat"
    println(myName.toCharArray()[1])
}

fun arrays() {
    val musketeers: Array<String> = arrayOf("Athos", "Porthos", "Aramis")
    println(musketeers[0])
    println(musketeers[1])
    println(musketeers[2])
    listOf(1, 2, 3, 5).toTypedArray()
}

