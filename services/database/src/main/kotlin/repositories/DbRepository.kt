package repositories

import Command
import DuplicateWalletException
import Repository
import WalletEvent
import exceptions.DuplicateTransactionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.postgresql.util.PSQLException
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

class DbRepository(private val connection: Connection): Repository {

    companion object Sql {
        const val INSERT_TRANSACTION_RECORD = "INSERT INTO transactions (transaction_id, coins_balance, version, wallet_id, transaction_type, coins) VALUES (?,?,?,?,?,?)"
        const val GET_LAST_TRANSACTION_BY_WALLET_ID = "SELECT * FROM transactions WHERE wallet_id=? order by version desc limit 1"
    }

    override suspend fun get(walletId: UUID): WalletEvent = withContext(Dispatchers.IO)  {
            val statement = connection.prepareStatement(GET_LAST_TRANSACTION_BY_WALLET_ID)
            statement.setObject(1, walletId)
            val resultSet = statement.executeQuery()
            if(resultSet.next()){
                return@withContext parseResultSet(resultSet)
            } else return@withContext WalletEvent(walletId = walletId)
    }

    override suspend fun save(newWalletEvent: WalletEvent): WalletEvent = withContext(Dispatchers.IO){

        try {
            val statement = connection.prepareStatement(INSERT_TRANSACTION_RECORD, Statement.RETURN_GENERATED_KEYS)
            statement.setString(1, newWalletEvent.transactionId)
            statement.setInt(2, newWalletEvent.coinsBalance)
            statement.setInt(3, newWalletEvent.version)
            statement.setObject(4, newWalletEvent.walletId)
            statement.setString(5, newWalletEvent.transactionType.toString())
            statement.setInt(6, newWalletEvent.coins)
            statement.executeUpdate()

            val generatedKeys = statement.generatedKeys
            if(generatedKeys.next()){
                return@withContext parseResultSet(resultSet = generatedKeys)
            }
            else throw Exception()
        } catch (psqlException: PSQLException) {
            if (psqlException.message!!.contains("duplicate_walletid_transaction")) throw DuplicateTransactionException()
            else throw psqlException
        }
    }

    private fun parseResultSet(resultSet: ResultSet): WalletEvent {
         return WalletEvent(
                 transactionId = resultSet.getString("transaction_id"),
                 version = resultSet.getInt("version"),
                 coinsBalance = resultSet.getInt("coins_balance"),
                 coins = resultSet.getInt("coins"),
                 transactionType = resultSet.getString("transaction_type")[0],
                 walletId=resultSet.getObject("wallet_id",UUID::class.java)
                 )
    }
}
