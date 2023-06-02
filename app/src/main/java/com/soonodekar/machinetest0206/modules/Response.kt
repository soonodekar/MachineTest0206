package com.soonodekar.machinetest0206.modules

data class Response(
    val error : Int,
    val total : Int,
    val books : ArrayList<Book>

)
