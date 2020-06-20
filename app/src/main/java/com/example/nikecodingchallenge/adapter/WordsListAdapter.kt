package com.example.nikecodingchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikecodingchallenge.R
import com.example.nikecodingchallenge.model.UrbanDictionaryResponse

class WordsListAdapter(val listItems: ArrayList<UrbanDictionaryResponse.Items>):
    RecyclerView.Adapter<WordsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val word = itemView.findViewById<TextView>(R.id.word)
        val definition = itemView.findViewById<TextView>(R.id.definition)
        val examples = itemView.findViewById<TextView>(R.id.example)
        val author = itemView.findViewById<TextView>(R.id.author)
        val thumbsUp = itemView.findViewById<TextView>(R.id.thumbs_up)
        val thumbsDown = itemView.findViewById<TextView>(R.id.thumbs_down)
        val publishDate = itemView.findViewById<TextView>(R.id.date)

        fun bindItem(listItems: UrbanDictionaryResponse.Items){

            word.text = listItems.word.toString()
            definition.text = listItems.definition.toString()
            examples.text = listItems.example.toString()
            author.text = listItems.author.toString()
            thumbsUp.text = listItems.thumbsUp.toString()
            thumbsDown.text = listItems.thumbsDown.toString()
            publishDate.text = listItems.writtenOn.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(listItems[position])
    }
}