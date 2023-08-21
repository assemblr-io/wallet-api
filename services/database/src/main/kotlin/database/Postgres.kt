package database

import java.sql.Connection
import java.sql.DriverManager

class PostgresConnection(){
    fun configure(connectionString: String): Connection{
        val connection = DriverManager.getConnection(connectionString)
        if (connection.isValid(0)) {
            println("Connected to Psql")
            return connection
        } else {
            throw Exception("Unable to configure connection to Psql ")
        }
    }
}