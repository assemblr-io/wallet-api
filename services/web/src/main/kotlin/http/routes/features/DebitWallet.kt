package http.routes.features

import DebitCommand
import http.Debit
import http.CommandResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import wallets.domain.handler.CommandHandler
import java.util.*

fun Route.debitWallet(commandHandler: CommandHandler){
    post("{walletId}/debit") {
        val walletId = UUID.fromString(call.parameters["walletId"])
        val request = call.receive<Debit>()
        val debitCommand = DebitCommand(request.transactionId, request.coins, walletId)
        val result = commandHandler.handle(debitCommand)
        call.respond(
            HttpStatusCode.Created,
            CommandResponse(result.transactionId, result.version, result.coinsBalance)
        )
    }
}