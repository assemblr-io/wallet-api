
import java.util.*

interface Query{
    val walletId: UUID
}

class BalanceQuery(override val walletId: UUID): Query