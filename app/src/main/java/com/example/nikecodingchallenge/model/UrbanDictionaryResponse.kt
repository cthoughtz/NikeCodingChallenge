package com.example.nikecodingchallenge.model


import com.google.gson.annotations.SerializedName

data class UrbanDictionaryResponse(
    @SerializedName("list")
    val list: List<Items>?
) {
    data class Items(
        @SerializedName("definition")
        val definition: String?,
        @SerializedName("permalink")
        val permalink: String?,
        @SerializedName("thumbs_up")
        val thumbsUp: Int?,
        @SerializedName("sound_urls")
        val soundUrls: List<String?>?,
        @SerializedName("author")
        val author: String?,
        @SerializedName("word")
        val word: String?,
        @SerializedName("defid")
        val defid: Int?,
        @SerializedName("current_vote")
        val currentVote: String?,
        @SerializedName("written_on")
        val writtenOn: String?,
        @SerializedName("example")
        val example: String?,
        @SerializedName("thumbs_down")
        val thumbsDown: Int?
    )
}