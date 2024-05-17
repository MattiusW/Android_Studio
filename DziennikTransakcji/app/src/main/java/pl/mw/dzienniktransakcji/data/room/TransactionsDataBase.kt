package pl.mw.dzienniktransakcji.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class], version = 1, exportSchema = false )
abstract class TransactionsDataBase : RoomDatabase() {
    abstract fun transactionsDao() : TransactionsDao

}