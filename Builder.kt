class MailBuilder {
    private var recipients: List<String> = listOf()
    private var cc: List<String> = listOf()
    private var title: String = ""
    private var message: String = ""
    private var important: Boolean = false

    class Mail constructor(
        val to: List<String>,
        val cc: List<String>?,
        val title: String?,
        val message: String?,
        val important: Boolean,
    )

    fun build(): Mail {
        if (recipients.isEmpty()) {
            throw RuntimeException("Please provide recipients")
        }

        return Mail(
            recipients,
            cc,
            title,
            message,
            important
        )
    }

    fun message(message: String = ""): MailBuilder {
        this.message = message
        return this
    }

    fun recipients(recipients: List<String>): MailBuilder {
        this.recipients = recipients
        return this
    }
}

fun main() {
    val newMail = MailBuilder()
        .recipients(listOf("hi@mail.com"))
        .message("Hello World!")
        .build()

    println(newMail.to)
    println(newMail.cc)
    println(newMail.message)
}