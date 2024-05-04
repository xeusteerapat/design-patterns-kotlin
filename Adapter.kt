import java.util.UUID
import java.util.stream.Stream

val list = listOf("a", "b", "c")

fun printStream(stream: Stream<String>) {
    stream.forEach { e ->
        println(e)
    }
}

class PaymentGateway {
    fun processPayment(amount: Double) {
        println("payment processing with $amount")
    }

    fun refundPayment(transactionId: String) {
        println("refund payment for $transactionId")
    }
}

interface NewPaymentGateway {
    fun authorizePayment(amount: Double): String
    fun reversePayment(transactionId: String)
}

class PaymentGatewayAdapter(
    private val paymentGateway: PaymentGateway
) : NewPaymentGateway {
    override fun authorizePayment(amount: Double): String {
        return UUID.randomUUID().toString()
    }

    override fun reversePayment(transactionId: String) {
        paymentGateway.refundPayment(transactionId)
    }
}

fun main() {
    printStream(list.stream())

    val legacyPaymentGateway = PaymentGateway()
    val paymentGateway: NewPaymentGateway = PaymentGatewayAdapter(legacyPaymentGateway)

    val transactionId = paymentGateway.authorizePayment(100.0)
    paymentGateway.reversePayment(transactionId)
}