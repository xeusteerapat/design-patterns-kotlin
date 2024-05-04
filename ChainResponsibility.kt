data class Request(
    val email: String,
    val question: String
) {
    fun isKnowEmail(): Boolean {
        return true
    }

    fun isFromJuniorDev(): Boolean {
        return false
    }
}

data class Response(
    val answer: String
)

typealias Handler = (request: Request) -> Response

val authentication = fun(next: Handler) =
    fun(request: Request): Response {
        if (!request.isKnowEmail()) {
            throw IllegalArgumentException()
        }

        return next(request)
    }

val basicValidation = fun(next: Handler) =
    fun(request: Request): Response {
        if (request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }

        return next(request)
    }

val finalResponse = fun() =
    fun(_: Request): Response {
        return Response("I don't know")
    }

fun main() {
    val req = Request(
        email = "dev@company.com",
        question = "Who broke my build?"
    )

    val chain = basicValidation(authentication(finalResponse()))

    val res = chain(req)

    println(res)
}
