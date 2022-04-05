package com.example.acalculator

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acalculator.databinding.FragmentHistoryBinding

private const val ARG_OPERATIONS = "param1"

class HistoryFragment : Fragment() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: FragmentHistoryBinding
    public var ops = ArrayList<OperationUi>()
    private val adapter = HistoryAdapter(::onOperationClick,::longOperationClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelableArrayList<OperationUi>(ARG_OPERATIONS)?.let {
            ops = it
        }
        Log.i(TAG,"${ops}")
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Historico"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        binding = FragmentHistoryBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.rvHistoric.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvHistoric.adapter = adapter
        adapter.updateItems(ops)
        /*adapter.updateItems(op)
        Log.i(TAG, "fragmento ...")
        Log.i(TAG, "${op}")*/
    }

    private fun onOperationClick(operation: OperationUi) {
        Log.i(TAG, "oalslsa")
        fragmentManager?.beginTransaction()
                ?.replace(R.id.frame, OperationDetailFragment.newInstance(operation))
                ?.addToBackStack(null)
                ?.commit()
    }

    private fun longOperationClick(operation: String) : Boolean {
        Toast.makeText(activity as Context, operation, Toast.LENGTH_SHORT).show()
        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(operations: List<OperationUi>) =
                HistoryFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_OPERATIONS, ArrayList(operations))
                    }
                }
    }

}