package plugins

import DuplicateWalletException
import NegativeBalanceException
import NegativeCoinsException
import ParsingException
import WalletNotFoundException
import exceptions.DuplicateTransactionException
import http.CommandResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptions() {
    install(StatusPages){
        exception<Throwable> { call, throwable ->
            when(throwable) {
                is DuplicateWalletException -> {
                    call.respond(
                        HttpStatusCode.Accepted,
                        CommandResponse(
                            throwable.eventData.transactionId,
                            throwable.eventData.version,
                            throwable.eventData.coinsBalance)
                    )
                }
                is WalletNotFoundException -> {
                    call.respond(
                        HttpStatusCode.NotFound,
                        throwable.message as String
                    )
                }
                is IllegalArgumentException,
                is DuplicateTransactionException,
                is NegativeCoinsException,
                is NegativeBalanceException,
                is ParsingException -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        throwable.message  as String
                    )
                }
            }
        }
    }
}

