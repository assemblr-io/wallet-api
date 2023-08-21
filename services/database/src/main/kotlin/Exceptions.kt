package exceptions

class DuplicateTransactionException(message: String= "Duplicate of old transaction"): Throwable(message)