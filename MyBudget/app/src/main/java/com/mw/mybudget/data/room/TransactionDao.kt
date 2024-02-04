package com.mw.mybudget.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mw.mybudget.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaction : Transaction)

    @Update
    fun updateTransaction(transaction : Transaction)

    @Delete
    fun deleteTransactions(transactions: List<Transaction>)

    @Query("SELECT * FROM transactions_table ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions_table WHERE type = 'INCOME' ")
    fun getAllIncomes(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions_table WHERE type = 'OUTCOME' ")
    fun getAllOutcomes(): Flow<List<Transaction>>

    @Query("SELECT category, SUM(price) as total FROM transactions_table WHERE type = 'INCOME' GROUP BY category")
    fun getSumOfIncomesGroupByCategory()
}