import java.util.*

enum class EventTypes(val code: Char) {
    CREDIT('C'),
    DEBIT('D')
}

class WalletEvent(
    var transactionId: String = "",
    var coins: Int = 0,
    var coinsBalance: Int = 0,
    var version: Int = 1,
    var transactionType: Char? = null,
    var walletId: UUID? = null
){

    fun credit(creditCommand: CreditCommand): WalletEvent{
        if (isDuplicate(creditCommand)) { throw DuplicateWalletException(eventData = this)}
        if (transactionId.isBlank()) {
            walletId  = creditCommand.walletId
            transactionId = creditCommand.transactionId
            coins=creditCommand.coins
            coinsBalance = creditCommand.coins
            transactionType = creditCommand.type.code
        } else {
            walletId  = creditCommand.walletId
            transactionId = creditCommand.transactionId
            coins=creditCommand.coins
            transactionType = creditCommand.type.code
            coinsBalance += creditCommand.coins
            version += 1
        }
        return this
    }

    fun debit(debitCommand: DebitCommand): WalletEvent{
        if(transactionId.isBlank()){throw WalletNotFoundException()}
        if(isDuplicate(debitCommand)) { throw DuplicateWalletException(eventData = this)}
        if(coinsBalance - debitCommand.coins <0){throw NegativeBalanceException()}
        transactionId = debitCommand.transactionId
        coins = debitCommand.coins
        coinsBalance -= debitCommand.coins
        version += 1
        transactionType = debitCommand.type.code
        walletId = debitCommand.walletId
        return this
    }

    private fun isDuplicate(command: Command): Boolean{
        return transactionId == command.transactionId
    }

}