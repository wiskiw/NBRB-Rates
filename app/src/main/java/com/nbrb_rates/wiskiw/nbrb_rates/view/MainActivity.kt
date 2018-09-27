package com.nbrb_rates.wiskiw.nbrb_rates.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.nbrb_rates.wiskiw.nbrb_rates.R
import com.nbrb_rates.wiskiw.nbrb_rates.model.CurrencyRateListWrapper
import com.nbrb_rates.wiskiw.nbrb_rates.view.fragment.ListFragment
import com.nbrb_rates.wiskiw.nbrb_rates.view.fragment.LoadingFragment
import com.nbrb_rates.wiskiw.nbrb_rates.view.fragment.MessageFragment
import com.nbrb_rates.wiskiw.nbrb_rates.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "NBRB-MainAct"
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(LoadingFragment.newInstance())

        mainViewModel.currencyRateList.observe(this,
                Observer<CurrencyRateListWrapper> {
                    if (it == null) return@Observer
                    it.error?.let { error ->
                        // something went wrong
                        Log.w(LOG_TAG, "Something went wrong", error)
                        replaceFragment(MessageFragment.newInstance(error.localizedMessage))
                    } ?: run {
                        // ok - date received
                        Log.d(LOG_TAG, "ok - date received")
                        replaceFragment(ListFragment.newInstance())
                    }
                })

    }

    private fun replaceFragment(frg: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, frg)
            commit()
        }
    }
}
