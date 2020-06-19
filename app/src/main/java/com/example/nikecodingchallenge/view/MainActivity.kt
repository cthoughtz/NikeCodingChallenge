package com.example.nikecodingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nikecodingchallenge.R
import com.example.nikecodingchallenge.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AppViewModel
    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up toolbar
        setSupportActionBar(toolbar)


        // set ViewModel
        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        // Observe backend for any changes
        observeBackEnd()

        // Fetch Data From Backend
        viewModel.getSearchResult("wat")
    }

    // Inflate view onto Toolbar - shows items in menu layout
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items,menu)
        return true
    }

    // Actions taken when items in toolbar are selected
    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {

        R.id.sort -> {
            //Todo - Sort Items based on thumbs
            true
        } else -> {
             super.onOptionsItemSelected(item)
        }

    }

    private fun observeBackEnd() {

        viewModel.searchResultSuccess.observe(this, Observer {

            val test = it.list!!.get(0).author
            Log.d(TAG,"Test: $test")
        })
    }
}