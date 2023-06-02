package com.soonodekar.machinetest0206.modules

import com.google.gson.annotations.SerializedName

data class Book(

    val title : String,
    val subtitle : String,
    val isbn13 : Long,
    val price : String,
    @SerializedName("image")
    val imageUrl : String,
    @SerializedName("url")
    val bookUrl : String
)
