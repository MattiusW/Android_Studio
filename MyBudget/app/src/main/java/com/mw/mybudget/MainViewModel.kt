package com.mw.mybudget

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mw.mybudget.data.TransactionsRepository
import com.mw.mybudget.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = TransactionsRepository(app.applicationContext)

    fun insertTransaction(transaction: Transaction) =
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertTransaction(transaction)
        }

    fun updateTransaction(transaction: Transaction) =
        CoroutineScope(Dispatchers.IO).launch {
            repo.updateTransaction(transaction)
        }

    fun deleteTransaction(transaction: List<Transaction>) =
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteTransactions(transaction)
        }

    fun getAllTransactions() = repo.getAllTransactions().asLiveData(viewModelScope.coroutineContext)
    fun getAllIncomes() = repo.getAllIncomes().asLiveData(viewModelScope.coroutineContext)
    fun getAllOutcomes() = repo.getAllIncomes().asLiveData(viewModelScope.coroutineContext)
    fun getSumOfIncomesByCategory() = repo.getSumOfIncomeByCategory().asLiveData(viewModelScope.coroutineContext)
    fun getSumOfOutcomesByCategory() = repo.getSumOfOutcomeByCategory().asLiveData(viewModelScope.coroutineContext)
}