package com.example.acalculator

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acalculator.databinding.ItemExpressionBinding

class HistoryAdapter(private val onOperationClick : (OperationUi) -> Unit,
                     private val longOperationClick : (String) -> Boolean,
                     private var items : List<OperationUi> = listOf()) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val TAG = MainActivity::class.java.simpleName

    class HistoryViewHolder(val binding: ItemExpressionBinding) :
            RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
                ItemExpressionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onOperationClick(items[position])
        }
        holder.itemView.setOnLongClickListener {
            longOperationClick(items[position].getTime())
        }


        holder.binding.textExpression.text = items[position].expression //items[position].getExpression()
        holder.binding.textResult.text = items[position].result //items[position].getResult()
        Log.i(TAG, "Item : ${items[position]}")
    }

    fun updateItems(items : List<OperationUi>){
        this.items = items
        notifyDataSetChanged()
    }
}