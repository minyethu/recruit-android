package nz.co.test.transactions.transactionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.services.Transaction
import java.time.format.DateTimeFormatter

class TransactionListAdapter(): RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    private var items: Array<Transaction> = emptyArray()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionDate: TextView
        val transactionSummary: TextView

        init {
            transactionDate = view.findViewById(R.id.transaction_date)
            transactionSummary = view.findViewById(R.id.transaction_summary)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionListAdapter.ViewHolder, position: Int) {
        //Set text on Transaction Date and Summary
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        holder.transactionDate.text = items[position].transactionDate.format(dateFormatter).toString()
        holder.transactionSummary.text = items[position].summary

        //Add onClick listener on the transaction card to navigate to TransactionDetailFragment.
        // Pass in each individual args to TransactionDetailsFragment
        holder.itemView.setOnClickListener {
            val id = items[position].id
            val summary = items[position].summary
            val transactionDate = items[position].transactionDate.toString()
            val credit = items[position].credit
            val debit = items[position].debit
            val action = TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailsFragment(id, transactionDate, summary, debit, credit)
                it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
       return items.size;
    }

    fun updateItems(newItems: Array<Transaction>) {
        items = newItems
        notifyDataSetChanged()
    }
}