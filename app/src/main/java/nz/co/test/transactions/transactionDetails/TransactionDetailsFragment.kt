package nz.co.test.transactions.transactionDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import nz.co.test.transactions.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionDetailsFragment : Fragment() {
    val args: TransactionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_transaction_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Get navigation args from TransactionList Fragment
        val credit = args.credit
        val debit = args.debit
        val summary = args.summary
        val transactionDate =  LocalDateTime.parse(args.transactionDate)

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")


        val dateTextView = view.findViewById<TextView>(R.id.transaction_detail_date)
        dateTextView.text = transactionDate.format(dateFormatter).toString()

        val timeTextView = view.findViewById<TextView>(R.id.transaction_detail_time)
        timeTextView.text = transactionDate.format(timeFormatter).toString()

        val summaryTextView = view.findViewById<TextView>(R.id.transaction_detail_summary)
        summaryTextView.text = summary

        val creditTextView = view.findViewById<TextView>(R.id.transaction_detail_credit)
        creditTextView.text = credit.toString()

        val debitTextView = view.findViewById<TextView>(R.id.transaction_detail_debit)
        debitTextView.text = debit.toString()



        super.onViewCreated(view, savedInstanceState)
    }

}
