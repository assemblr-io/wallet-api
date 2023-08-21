package http

import kotlinx.serialization.Serializable


@Serializable
data class CommandResponse(
    val transactionId: String,
    val version: Int,
    val coins: Int
)

@Serializable
data class BalanceQueryResponse(
    val transactionId: String,
    val version: Int,
    val coins: Int
)
