package com.soonodekar.machinetest0206.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soonodekar.machinetest0206.Repo.BooksRepo
import com.soonodekar.machinetest0206.ViewModel.BooksViewModel
import com.soonodekar.machinetest0206.network.WebService

class ViewMoldeFactory(private val booksRepo: BooksRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(booksRepo) as T
        }

        throw java.lang.Exception()
    }
}