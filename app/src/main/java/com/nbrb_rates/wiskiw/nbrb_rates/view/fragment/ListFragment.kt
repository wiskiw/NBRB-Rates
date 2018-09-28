package com.nbrb_rates.wiskiw.nbrb_rates.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
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
        private const val RECYCLER_VIEW_STATE_KEY = "recycler_view_state"
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    private var recyclerSate: Parcelable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val frgView = inflater.inflate(R.layout.fragment_list, container, false)

        mainViewModel.currencyRateList.observe(this,
                Observer<CurrencyRateListWrapper> {

                    // setup layout manager
                    frgView.recycler_view.layoutManager = LinearLayoutManager(activity)

                    // setup adapter
                    val adapter = RateRecyclerViewAdapter(activity!!)
                    frgView.recycler_view.adapter = adapter

                    // fill adapter
                    it?.data?.let { items -> adapter.refill(items) }

                    // restore scroll position
                    recyclerSate?.let { recyclerSate ->
                        frgView.recycler_view.layoutManager?.onRestoreInstanceState(recyclerSate)
                    }
                })

        return frgView
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // restore recycler view state
        if (recyclerSate == null && savedInstanceState != null) {
            recyclerSate = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save recycler view state
        recyclerSate = view?.recycler_view?.layoutManager?.onSaveInstanceState()
        outState.putParcelable(RECYCLER_VIEW_STATE_KEY, recyclerSate)
    }
}
