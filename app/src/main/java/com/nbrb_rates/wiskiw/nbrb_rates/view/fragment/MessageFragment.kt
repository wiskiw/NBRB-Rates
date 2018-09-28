package com.nbrb_rates.wiskiw.nbrb_rates.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nbrb_rates.wiskiw.nbrb_rates.R
import kotlinx.android.synthetic.main.fragment_message.view.*

class MessageFragment : Fragment() {

    companion object {

        private const val MSG_BUNDLE_KEY = "bundle_msg"

        @JvmStatic
        fun newInstance(msg: String): MessageFragment {
            val bundle = Bundle().apply {
                putString(MSG_BUNDLE_KEY, msg)
            }

            return MessageFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val frgView = inflater.inflate(R.layout.fragment_message, container, false)

        arguments?.getString(MSG_BUNDLE_KEY)?.let {
            frgView.message_textview.text = it
        }
        return frgView
    }


}
