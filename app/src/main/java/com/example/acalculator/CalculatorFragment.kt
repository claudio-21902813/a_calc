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
import com.example.acalculator.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_OPERATIONS = "param1"
private const val ARG_OPERATIONS2 = "param2"

class CalculatorFragment : Fragment() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: FragmentCalculatorBinding
    private var ops = ArrayList<OperationUi>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelableArrayList<OperationUi>(ARG_OPERATIONS)?.let {
            ops = it
        }
        Log.i(TAG, "${ops}")
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Calculadora"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_calculator,
            container,
            false
        )
        binding = FragmentCalculatorBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // 1 Linha
        binding.button0.setOnClickListener { onClickSymbol("0")}
        binding.button00?.setOnClickListener { onClickSymbol("00")}
        binding.buttonDot?.setOnClickListener { onClickSymbol(".")}
        binding.buttonAdition.setOnClickListener { onClickSymbol("+") }

        // 2 Linha
        binding.button1.setOnClickListener { onClickSymbol("1") }
        binding.button2.setOnClickListener { onClickSymbol("2") }
        binding.button2Aux?.setOnClickListener { onClickSymbol("2") }
        binding.button3.setOnClickListener { onClickSymbol("3") }
        binding.buttonSubtract?.setOnClickListener { onClickSymbol("-") }

        // 3 Linha
        binding.button4.setOnClickListener { onClickSymbol("4") }
        binding.button5.setOnClickListener { onClickSymbol("5") }
        binding.button6.setOnClickListener { onClickSymbol("6") }
        binding.button7?.setOnClickListener { onClickSymbol("7") }
        binding.buttonMultiplication?.setOnClickListener { onClickSymbol("*") }

        // 4 Linha
        binding.button8?.setOnClickListener { onClickSymbol("8") }
        binding.button9?.setOnClickListener { onClickSymbol("9") }
        binding.buttonCe?.setOnClickListener { onClickSymbol("ce") }
        binding.buttonLess?.setOnClickListener { onClickSymbol("<") }
        binding.buttonDivision?.setOnClickListener { onClickSymbol("/") }

        binding.buttonEquals.setOnClickListener { onClickEquals() }
    }

    private fun onClickSymbol(symbol : String) {
        when(symbol){
            "0" -> {
                binding.textVisor.append(symbol)
            }
            "00" -> {
                binding.textVisor.append(symbol)
            }
            "." -> {
                binding.textVisor.append(symbol)
            }
            "1" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "2" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "3" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "4" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "5" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "6" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "7" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "8" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "9" -> {
                if (binding.textVisor.text == "0")
                    binding.textVisor.text = symbol
                else
                    binding.textVisor.append(symbol)
            }
            "+" -> {
                Log.i(TAG, "Click no botão +")
                binding.textVisor.append("+")
            }
            "*" -> {
                Log.i(TAG, "Click no botão +")
                binding.textVisor.append("*")
            }
            "-" -> {
                Log.i(TAG, "Click no botão +")
                binding.textVisor.append("-")
            }
            "/" -> {
                Log.i(TAG, "Click no botão +")
                binding.textVisor.append("/")
            }
            "ce" -> {
                binding.textVisor.text = "";
            }
            "<" -> {
                binding.textVisor.text = "";
            }
        }
    }
    private fun onClickEquals() {
        val expression = ExpressionBuilder(
            binding.textVisor.text.toString()
        ).build()

        (activity as MainActivity).addElem(
                OperationUi(binding.textVisor.text.toString(),
                        expression.evaluate().toString(),
                        Calendar.getInstance().timeInMillis
                )
        )
        Log.i(TAG, "${ops}")
        binding.textVisor.text = expression.evaluate().toString()
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
                CalculatorFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_OPERATIONS, ArrayList(operations))
                    }
                }
    }

}