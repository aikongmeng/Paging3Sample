package com.example.paging3sample.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("language") val language: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val starCount: Int,
 )