package com.example.nikecodingchallenge.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.example.nikecodingchallenge.R
import com.example.nikecodingchallenge.model.UrbanDictionaryResponse

class WordsListAdapter(val listItems: ArrayList<UrbanDictionaryResponse.Items>):
    RecyclerView.Adapter<WordsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View,context: Context): RecyclerView.ViewHolder(itemView){

        val ctx = context

        val word = itemView.findViewById<TextView>(R.id.word)
        val definition = itemView.findViewById<TextView>(R.id.definition)
        val examples = itemView.findViewById<TextView>(R.id.example)
        val author = itemView.findViewById<TextView>(R.id.author)
        val thumbsUp = itemView.findViewById<TextView>(R.id.thumbs_up)
        val thumbsDown = itemView.findViewById<TextView>(R.id.thumbs_down)
        val publishDate = itemView.findViewById<TextView>(R.id.date)
        val shareButton = itemView.findViewById<ImageButton>(R.id.share_button)

        fun bindItem(listItems: UrbanDictionaryResponse.Items){

            word.text = listItems.word.toString()
            definition.text = convertRawData(listItems.definition.toString())
            examples.text = convertRawData(listItems.example.toString())
            author.text = listItems.author.toString()
            thumbsUp.text = listItems.thumbsUp.toString()
            thumbsDown.text = listItems.thumbsDown.toString()
            publishDate.text = listItems.writtenOn.toString()

            shareButton.setOnClickListener{

                var defWord = listItems.word.toString()
                var def = listItems.definition.toString()

                var wordAndDef = "$defWord \n $def"

                var dataIntent = Intent(Intent.ACTION_SEND)
                dataIntent.setType("text/plain")
                dataIntent.putExtra(Intent.EXTRA_TEXT,wordAndDef)
                ctx.startActivity(Intent.createChooser(dataIntent,"Definition"))
            }
        }

        private fun convertRawData(toString: String): String {

            return toString.replace("[","").replace("]","")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(v,v.context)
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(listItems[position])
    }
}