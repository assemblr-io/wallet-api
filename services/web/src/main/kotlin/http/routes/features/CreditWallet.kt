package http.routes.features

import CreditCommand
import http.Credit
import http.CommandResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import wallets.domain.handler.CommandHandler
import java.util.*

fun Route.creditWallet(commandHandler: CommandHandler){
    post("{walletId}/credit") {
        val walletId = UUID.fromString(call.parameters["walletId"])
        val request = call.receive<Credit>()
        val creditCommand = CreditCommand(request.transactionId, request.coins, walletId)
        val result = commandHandler.handle(creditCommand)
        call.respond(
            HttpStatusCode.Created,
            CommandResponse(result.transactionId, result.version, result.coinsBalance)
        )
    }
}