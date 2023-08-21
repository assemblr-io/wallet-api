package http

import NegativeBalanceException
import ParsingException
import kotlinx.serialization.Serializable

@Serializable
data class Credit(val transactionId: String, val coins: Int){
    init{
        require(coins >= 0) {throw NegativeBalanceException()}
        require(transactionId.isNotBlank()) {throw ParsingException()}
    }
}

@Serializable
data class Debit(val transactionId: String, val coins: Int){
    init{
        require(coins >= 0) {throw NegativeBalanceException()}
        require(transactionId.isNotBlank()) {throw ParsingException()}
    }
}