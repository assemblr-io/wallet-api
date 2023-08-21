package wallets.domain.handler

import Query
import Repository
import WalletEvent
import WalletNotFoundException

class QueryHandler(private val repository: Repository){

    suspend fun handle(balanceQuery: Query): WalletEvent {
        val lastTransactionEvent = repository.get(balanceQuery.walletId)
        return if(lastTransactionEvent.transactionId.isNotBlank()) lastTransactionEvent
        else throw WalletNotFoundException()
    }
}
