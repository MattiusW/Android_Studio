package com.mw.dzienniktransakcji.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mw.dzienniktransakcji.data.models.CategoryTotal
import com.mw.dzienniktransakcji.data.models.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDao {

    @Insert
    fun insertTransactions(transaction: Transaction)

    @Update
    fun updateTransactions(transaction: Transaction)

    @Delete
    fun deleteTransactions(transactions: List<Transaction>)
    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE type = 'INCOME'")
    fun getAllIncomes(): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE type = 'OUTCOME'")
    fun getAllOutcomes(): Flow<List<Transaction>>

    @Query("SELECT category, SUM(price) as total FROM transaction_table WHERE type = 'INCOME' GROUP BY category")
    fun getSumOfIncomesGroupByCategory(): Flow<List<CategoryTotal>>

    @Query("SELECT category, SUM(price) as total FROM transaction_table WHERE type = 'OUTCOME' GROUP BY category")
    fun getSumOfOutcomesGroupByCategory(): Flow<List<CategoryTotal>>
}