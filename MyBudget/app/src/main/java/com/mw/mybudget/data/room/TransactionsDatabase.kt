package com.mw.mybudget.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mw.mybudget.data.model.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract fun transactionsDao() : TransactionDao
}