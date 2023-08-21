

class DuplicateWalletException(message: String = "Duplicate Transaction", val eventData: WalletEvent): Throwable(message)
class NegativeBalanceException(message: String = "Negative balances not allowed"): Throwable(message)
class NegativeCoinsException(message: String = "Negative Coins Balance"): Throwable(message)
class WalletNotFoundException(message: String = "Wallet not found"): Throwable(message)
class ParsingException(message: String= "Bad client request"): Throwable(message)