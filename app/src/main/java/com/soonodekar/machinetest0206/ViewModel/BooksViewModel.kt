package com.soonodekar.machinetest0206.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soonodekar.machinetest0206.Repo.BooksRepo
import com.soonodekar.machinetest0206.modules.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BooksViewModel(
    private val booksRepo: BooksRepo
) : ViewModel(){

    val booksLiveData = MutableLiveData<ArrayList<Book>>()

    fun getBooks(){
        CoroutineScope(Dispatchers.IO).launch {

            val response = booksRepo.getBooks()

            withContext(Dispatchers.Main){

                booksLiveData.postValue(response.books)
            }
        }
    }

}