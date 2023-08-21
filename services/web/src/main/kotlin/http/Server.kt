package http

import http.routes.configureRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import plugins.configureExceptions
import plugins.configureSerialisation
import wallets.domain.handler.CommandHandler
import wallets.domain.handler.QueryHandler


class Server(private val queryHandler: QueryHandler, private val commandHandler: CommandHandler, private val port: Int) {
    fun start() {
        embeddedServer(
            Jetty, port = port,
            module = {
                configureExceptions()
                configureSerialisation()
                configureRouting(queryHandler, commandHandler)
            }
        ).start(wait = true)
    }
}
