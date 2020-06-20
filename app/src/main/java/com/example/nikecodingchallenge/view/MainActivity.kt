package com.example.nikecodingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikecodingchallenge.R
import com.example.nikecodingchallenge.adapter.WordsListAdapter
import com.example.nikecodingchallenge.model.UrbanDictionaryResponse
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


        //Search Query
        searchQuery()

    }

    private fun searchQuery() {

        // Get EditText that is in search view
        var searchEditText = search_view.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)

        searchEditText.setOnEditorActionListener { v, actionId, event ->

            // if actionId equals searched button
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                // get search query
                var query = search_view.query.toString()

                if (query != null) {
                    // Fetch Data From Backend
                    viewModel.getSearchResult(query)
                } else{
                    viewModel.getSearchResult("wat")
                }

                // show progress bar
                progress_bar.visibility = View.VISIBLE
            }

            true
        }

    }

    private fun setUpRecyclerView(it: UrbanDictionaryResponse) {

        val wordList = ArrayList<UrbanDictionaryResponse.Items>()
        val wordListAdapter = WordsListAdapter(wordList)

        //Add to List
        addDataArrayList(wordList,it,wordListAdapter)

        recyclerView.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = wordListAdapter
        }
    }

    // add Items to arrayList
    private fun addDataArrayList(
        wordList: java.util.ArrayList<UrbanDictionaryResponse.Items>,
        it: UrbanDictionaryResponse,
        wordListAdapter: WordsListAdapter
    ) {
        var word = ""
        var definition = ""
        var example = ""
        var author = ""
        var thumbsUp = ""
        var thumbsDown = ""
        var date = ""
        var size = it.list!!.size

        // Loop Through data
        for (i in 0 until size) {

            word = it.list.get(i).word.toString()
            definition = it.list.get(i).definition.toString()
            example = it.list.get(i).example.toString()
            author = it.list.get(i).author.toString()
            thumbsUp = it.list.get(i).thumbsUp.toString()
            thumbsDown = it.list.get(i).thumbsDown.toString()
            date = it.list.get(i).writtenOn.toString()

            // Format Date
            var formatted = formattedDate(date)

            // Add data to List to be displayed
            wordList.add(UrbanDictionaryResponse.Items(
                definition,null,thumbsUp.toInt(),null,author,word,null,null,
                formatted,example,thumbsDown.toInt()
            ))
        }

        // Notify Adapter of change
        wordListAdapter!!.notifyDataSetChanged()
    }

    private fun formattedDate(date: String): String {

        // Get the first 10 digits of string
        var trimmedDate = date.substring(0,9)

        // Split string based on dash(-)
        var splitDate = trimmedDate.split("-")

        // Create Final Date
        var finalDate = "${splitDate[1]}/${splitDate[2]}/${splitDate[0]}"

        return finalDate
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

            //Set up recyclerView
            setUpRecyclerView(it)
        })

    }
}