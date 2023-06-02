package com.soonodekar.machinetest0206.network

import com.soonodekar.machinetest0206.modules.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    @GET("1.0/new")

    suspend fun getBooks() : Response

    companion object{

        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.itbook.store/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val webService = retrofit.create(WebService ::class.java)

        fun getWebService() : WebService = webService
    }


}