package nz.co.test.transactions.transactionList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsApi

class TransactionListViewModel: ViewModel() {
    private val _transactions = MutableLiveData<Array<Transaction>>()

    val transactions: LiveData<Array<Transaction>> = _transactions

    init {
        _transactions.value = emptyArray()
        retrieveTransactionsFromAPI()
    }

    fun retrieveTransactionsFromAPI() {
        viewModelScope.launch {
            try {
               val result = TransactionsApi.retrofitService.retrieveTransactions()
                _transactions.value = result
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}