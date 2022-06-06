package nz.co.test.transactions.transactionList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.services.Transaction

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
        holder.transactionDate.text = items[position].transactionDate.toString()
        holder.transactionSummary.text = items[position].summary
    }

    override fun getItemCount(): Int {
       return items.size;
    }

    fun updateItems(newItems: Array<Transaction>) {
        items = newItems
        notifyDataSetChanged()
    }
}