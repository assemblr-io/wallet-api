
import java.util.*


interface Repository{
    suspend fun get(walletId: UUID): WalletEvent
    suspend fun save(newWalletEvent: WalletEvent): WalletEvent
}
