package com.mw.mybudget.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.mw.mybudget.data.model.Transaction

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaction : Transaction)

    @Update
    fun updateTransaction(transaction : Transaction)

    @Delete
    fun deleteTransactions(transactions: List<Transaction>)
}