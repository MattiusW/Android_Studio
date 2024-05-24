package pl.mw.dzienniktransakcji

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.mw.dzienniktransakcji.data.TransactionsRepository
import pl.mw.dzienniktransakcji.data.room.Transaction

class MainViewModel(app: Application): AndroidViewModel(app) {
    var isBottomNavVisible = true
    private var selectedTransaction: Transaction? = null
    private val repo = TransactionsRepository(app.applicationContext)

    fun insertTransaction(transaction: Transaction) =
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertTransaction(transaction)
        }

    fun updateTransaction(transaction: Transaction) =
        CoroutineScope(Dispatchers.IO).launch {
            repo.updateTransaction(transaction)
        }

    fun deleteTransaction(transactions: List<Transaction>) =
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteTransactions(transactions)
        }

    fun getAllTransactions() = repo.getAllTransactions().asLiveData(viewModelScope.coroutineContext)
    fun getAllIncomes() = repo.getAllIncomes().asLiveData(viewModelScope.coroutineContext)
    fun getAllOutcomes() = repo.getAllOutcomes().asLiveData(viewModelScope.coroutineContext)
    fun getSumOfIncomeByCategory() = repo.getSumOfIncomeByCategory().asLiveData(viewModelScope.coroutineContext)
    fun getSumOfOutcomeByCategory() = repo.getSumOfOutcomesByCategory().asLiveData(viewModelScope.coroutineContext)

    fun selectTransaction(transaction: Transaction) {
        selectedTransaction = transaction
    }

    fun unselectTransaction() {
        selectedTransaction = null
    }

    fun getSelectedTransaction() = selectedTransaction

}