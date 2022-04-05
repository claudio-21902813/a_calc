package com.example.acalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.acalculator.databinding.FragmentHistoryBinding
import com.example.acalculator.databinding.FragmentOperationDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [OperationDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OperationDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentOperationDetailBinding
    private var param1: OperationUi? = null
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_operation_detail, container, false)
        binding = FragmentOperationDetailBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "param : ${param1}")
        binding.expression.text = param1?.expression
        binding.result.text = param1?.result
        binding.time.text = param1?.getTime()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OperationDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(op: OperationUi) =
            OperationDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, op)
                }
            }
    }
}