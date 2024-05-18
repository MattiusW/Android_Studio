package pl.mw.dzienniktransakcji.data

import android.content.Context
import pl.mw.dzienniktransakcji.data.room.DatabaseInstance
import pl.mw.dzienniktransakcji.data.room.Transaction

class TransactionsRepository(context: Context) {
    private val transactionsDao = DatabaseInstance.getInstance(context).transactionsDao()
    suspend fun insertTransaction(transaction: Transaction) {
        transactionsDao.insertTransaction(transaction)
    }
    suspend fun updateTransaction(transaction: Transaction) {
        transactionsDao.updateTransaction(transaction)
    }
    suspend fun deleteTransactions(transactions: List<Transaction>) {
        transactionsDao.deleteTransactions(transactions)
    }

    fun getAllTransactions() = transactionsDao.getAllTransactions()
    fun getAllIncomes() = transactionsDao.getAllIncomes()
    fun getAllOutcomes() = transactionsDao.getAllOutcomes()
    fun getSumOfIncomeByCategory() = transactionsDao.getSumOfIncomesGroupByCategory()
    fun getSumOfOutcomesByCategory() = transactionsDao.getSumOfOutcomesGroupByCategory()
}