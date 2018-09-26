package com.nbrb_rates.wiskiw.nbrb_rates.view.fragment


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nbrb_rates.wiskiw.nbrb_rates.R
import com.nbrb_rates.wiskiw.nbrb_rates.viewmodel.MainViewModel

class ListFragment() : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }


    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


}
