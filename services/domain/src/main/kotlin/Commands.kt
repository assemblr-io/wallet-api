import java.util.*

interface Command{
    val transactionId: String
    val coins: Int
    val walletId: UUID
    val type: EventTypes
}

data class CreditCommand(
    override val transactionId: String,
    override val coins: Int,
    override val walletId: UUID,
    override val type: EventTypes = EventTypes.CREDIT
): Command {
    init {
        require(walletId.toString().isNotBlank()) { throw ParsingException("Parsing Exception: wallet id is empty") }
        require(transactionId.isNotBlank()) {throw ParsingException("Parsing Exception: transaction id is empty")}
        require(coins >=0){throw NegativeCoinsException()}
    }
}

data class DebitCommand(
    override val transactionId: String,
    override val coins: Int,
    override val walletId: UUID,
    override val type: EventTypes = EventTypes.DEBIT
): Command {
    init {
        require(walletId.toString().isNotBlank()) { throw ParsingException("Parsing Exception: wallet id is empty") }
        require(transactionId.isNotBlank()) {throw ParsingException("Parsing Exception: transaction id is empty")}
        require(coins >=0){throw NegativeCoinsException()}
    }
}
