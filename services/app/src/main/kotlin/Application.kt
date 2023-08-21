package vgw.wallets.lnd

import database.PostgresConnection
import http.Server
import repositories.DbRepository
import repositories.InMemRepository
import vgw.wallets.lnd.Environment.dbConnectionString
import vgw.wallets.lnd.Environment.repositoryImplementation
import wallets.domain.handler.CommandHandler
import wallets.domain.handler.QueryHandler

fun main(){
    val repository = when (val arg = repositoryImplementation) {
        "dev" -> InMemRepository()
        "prod"-> DbRepository(PostgresConnection().configure(dbConnectionString))
        else -> throw RuntimeException("ENVIRONMENT variable $arg not set. Must be 'dev' or 'prod'")
    }
    Server(QueryHandler(repository), CommandHandler(repository), Environment.port).start()
}



