package nz.co.test.transactions.transactionList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.services.Transaction

class TransactionListFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerView.Adapter<TransactionListAdapter.ViewHolder>
    private lateinit var recyclerView:RecyclerView

    private val viewModel: TransactionListViewModel by lazy {
        ViewModelProvider(this).get(TransactionListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate Transaction fragment and setup recycler view
        val view = inflater.inflate(R.layout.fragment_transaction_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        adapter = TransactionListAdapter()
        recyclerView.adapter = adapter

        //Observer for transactions
        val transactionsObserver  = Observer<Array<Transaction>> { transactions ->
            (adapter as TransactionListAdapter).updateItems(transactions)
        }
        viewModel.transactions.observe(viewLifecycleOwner, transactionsObserver)

        return view;
    }

}
