package com.soonodekar.machinetest0206.Repo

import com.soonodekar.machinetest0206.network.WebService
import com.soonodekar.machinetest0206.modules.Response

class BooksRepo(
    private val webService: WebService
) {
    suspend fun getBooks() : Response{
        return webService.getBooks()
    }
}