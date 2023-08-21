package http.routes.features

import BalanceQuery
import http.BalanceQueryResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import wallets.domain.handler.QueryHandler
import java.util.*

fun Route.walletBalance(queryHandler: QueryHandler){
    get("{walletId}") {
        val walletId = UUID.fromString(call.parameters["walletId"])
        val balanceQuery = BalanceQuery(walletId)
        val result = queryHandler.handle(balanceQuery)
        call.respond(
            HttpStatusCode.OK,
            BalanceQueryResponse(result.transactionId , result.version, result.coinsBalance)
        )
    }
}