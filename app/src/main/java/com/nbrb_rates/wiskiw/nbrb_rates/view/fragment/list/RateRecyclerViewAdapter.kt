package com.nbrb_rates.wiskiw.nbrb_rates.view.fragment.list

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nbrb_rates.wiskiw.nbrb_rates.R
import com.nbrb_rates.wiskiw.nbrb_rates.model.CurrencyRate
import kotlinx.android.synthetic.main.currency_list_item.view.*
import java.util.*


class RateRecyclerViewAdapter(val context: Context) :
        RecyclerView.Adapter<RateRecyclerViewAdapter.RateViewHolder>(),
        DragDropItemTouchHelperCallback.DragDropItemTouchHelperAdapter {

    private var items: List<CurrencyRate> = emptyList()

    fun refill(items: List<CurrencyRate>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int):
            RateRecyclerViewAdapter.RateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.currency_list_item,
                viewGroup, false)
        return RateViewHolder(view)
    }

    override fun getItemCount() = items.size

    // IDE annotation
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RateRecyclerViewAdapter.RateViewHolder, pos: Int) {
        val rateItem = items[pos]

        holder.apply {
            idTextView.text = rateItem.id.toString()
            nameTextView.text = rateItem.name
            charCodeTextView.text = rateItem.charCode
            rateTextView.text = "%.2f".format(rateItem.rate)
            scaleTextView.text = rateItem.scale.toString()
            numCodeTextView.text = rateItem.numCode.toString()
        }

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < items.size && toPosition < items.size) {
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(items, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(items, i, i - 1)
                }
            }
            notifyItemMoved(fromPosition, toPosition)
        }
        return true
    }

    // IDE annotation
    @Suppress("HasPlatformType")
    class RateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView = view.id_text
        val nameTextView = view.name_text
        val charCodeTextView = view.char_code_text
        val rateTextView = view.rate_text
        val scaleTextView = view.scale_text
        val numCodeTextView = view.num_code_text
    }

}