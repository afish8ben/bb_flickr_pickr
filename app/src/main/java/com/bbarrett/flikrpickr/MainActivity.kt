package com.bbarrett.flikrpickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bbarrett.flikrpickr.ui.fragments.FlickrResultsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FlickrResultsFragment.newInstance())
                .commitNow()
        }
    }

}
