package com.nbrb_rates.wiskiw.nbrb_rates.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nbrb_rates.wiskiw.nbrb_rates.R

class LoadingFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = LoadingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }


}
