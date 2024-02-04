package com.mw.mybudget.data

import android.app.Application
import android.content.Context
import com.mw.mybudget.data.model.Transaction
import com.mw.mybudget.data.room.DatabaseInstance

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
    fun getSumOfOutcomeByCategory() = transactionsDao.getSumOfOutcomesGroupByCategory()
}