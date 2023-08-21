package repositories

import Command
import DuplicateWalletException
import EventReadStore
import Repository
import WalletEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class InMemRepository: Repository, EventReadStore {
    override val eventStore = mutableListOf<WalletEvent>()

    override suspend fun get(walletId: UUID): WalletEvent = withContext(Dispatchers.IO) {
        return@withContext eventStore.filter { it.walletId == walletId }.maxByOrNull{ it.version }
        ?: WalletEvent(
            walletId = walletId
        )
    }

    override suspend fun save(newWalletEvent: WalletEvent): WalletEvent {
        eventStore.add(newWalletEvent)
        return newWalletEvent
    }
}



