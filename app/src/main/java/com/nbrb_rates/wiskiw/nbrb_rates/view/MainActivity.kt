package com.nbrb_rates.wiskiw.nbrb_rates.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nbrb_rates.wiskiw.nbrb_rates.R
import com.nbrb_rates.wiskiw.nbrb_rates.repository.CurrencyRateRepository
import com.nbrb_rates.wiskiw.nbrb_rates.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.debug(CurrencyRateRepository(this))

    }

    private fun replaceFragment(frg: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, frg)
            commit()
        }
    }
}
