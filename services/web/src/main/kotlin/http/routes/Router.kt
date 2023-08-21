package http.routes

import http.routes.features.creditWallet
import http.routes.features.debitWallet
import http.routes.features.walletBalance
import io.ktor.server.application.*
import io.ktor.server.routing.*
import wallets.domain.handler.CommandHandler
import wallets.domain.handler.QueryHandler

fun Application.configureRouting(queryHandler: QueryHandler, transactionHandler: CommandHandler) {

    routing {
        route("/wallets"){
            creditWallet(transactionHandler)
            debitWallet(transactionHandler)
            walletBalance(queryHandler)
        }
    }
}