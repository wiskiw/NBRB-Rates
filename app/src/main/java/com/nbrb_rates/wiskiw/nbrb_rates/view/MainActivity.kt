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

        private const val LIST_FRAGMENT_TAG = "List-Fragment"
        private const val MSG_FRAGMENT_TAG = "Message-Fragment"
        private const val LOADING_FRAGMENT_TAG = "Loading-Fragment"
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val listFragment by lazy {
        ListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel.currencyRateList.observe(this,
                Observer<CurrencyRateListWrapper> {
                    Log.d(LOG_TAG, "Data state: ${it?.state}")
                    if (it == null) return@Observer

                    //Checking data state
                    when (it.state) {
                        CurrencyRateListWrapper.State.LOADING -> {
                            replaceFragment(LoadingFragment.newInstance(), LOADING_FRAGMENT_TAG)
                        }

                        CurrencyRateListWrapper.State.DONE -> {
                            // ok - date received
                            replaceFragment(listFragment, LIST_FRAGMENT_TAG)
                        }

                        CurrencyRateListWrapper.State.ERROR -> {
                            // something went wrong
                            replaceFragment(
                                    MessageFragment.newInstance(it.error?.localizedMessage ?: ""),
                                    MSG_FRAGMENT_TAG
                            )
                        }
                    }
                })
    }

    private fun replaceFragment(frg: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        if (currentFragment == null || currentFragment.isVisible) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, frg, tag)
                commit()
            }
        }
    }
}
