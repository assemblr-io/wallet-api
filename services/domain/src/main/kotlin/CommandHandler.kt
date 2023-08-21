package wallets.domain.handler

import CreditCommand
import DebitCommand
import Repository
import WalletEvent
import WalletNotFoundException


class CommandHandler(private val repository: Repository) {

    suspend fun handle(creditCommand: CreditCommand): WalletEvent {
        val lastTransactionEvent =  repository.get(creditCommand.walletId)
        val newTransactionEvent = lastTransactionEvent.credit(creditCommand)
        return repository.save(newTransactionEvent)
    }

    suspend fun handle(debitCommand: DebitCommand): WalletEvent {
        val lastTransactionEvent =  repository.get(debitCommand.walletId)
        val newTransactionEvent =  lastTransactionEvent.debit(debitCommand)
        return repository.save(newTransactionEvent)
    }
}


