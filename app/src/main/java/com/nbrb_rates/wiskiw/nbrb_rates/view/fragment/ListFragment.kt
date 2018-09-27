package com.nbrb_rates.wiskiw.nbrb_rates.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nbrb_rates.wiskiw.nbrb_rates.R
import com.nbrb_rates.wiskiw.nbrb_rates.model.CurrencyRateListWrapper
import com.nbrb_rates.wiskiw.nbrb_rates.view.fragment.list.RateRecyclerViewAdapter
import com.nbrb_rates.wiskiw.nbrb_rates.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment() : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()

        private const val LOG_TAG = "NBRB-ListFrg"
    }


    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val frgView = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = RateRecyclerViewAdapter(activity!!)

        frgView.recycler_view.layoutManager = LinearLayoutManager(activity)
        frgView.recycler_view.adapter = adapter


        mainViewModel.currencyRateList.observe(this,
                Observer<CurrencyRateListWrapper> {
                    it?.data?.let { items -> adapter.refill(items) }
                })
        return frgView
    }


}
