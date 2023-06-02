package com.soonodekar.machinetest0206.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.soonodekar.machinetest0206.R
import com.soonodekar.machinetest0206.Repo.BooksRepo
import com.soonodekar.machinetest0206.ViewModel.BooksViewModel
import com.soonodekar.machinetest0206.adaptors.BooksAdaptor
import com.soonodekar.machinetest0206.databinding.BooksListFragmentBinding
import com.soonodekar.machinetest0206.modules.Book
import com.soonodekar.machinetest0206.network.WebService
import com.soonodekar.machinetest0206.provider.ViewMoldeFactory

class BookListFragment : Fragment() {

    //private lateinit var requestQueue : RequestQueue
    private lateinit var binding: BooksListFragmentBinding
    private lateinit var booksViewModel: BooksViewModel
    private lateinit var booksAdaptor: BooksAdaptor
    private val books = ArrayList<Book>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.books_list_fragment, null)
        binding = BooksListFragmentBinding.bind(view)

       //requestQueue = Volley.newRequestQueue(requireContext())

        initViewModel()
        initObserver()
        //getBooks()
        booksViewModel.getBooks()
        initViewsAndAdaptor()
        setUpListeners()
        return view
    }

    private fun setUpListeners(){
        booksAdaptor.bookClickListeners =
            object : BooksAdaptor.BookClickLisetner {
                override fun onBookClick(book: Book) {

                    val bookDetailsFragment = BookDetailsFragment()
                    bookDetailsFragment.book = book
                    parentFragmentManager.beginTransaction()
                        .add(R.id.mainContainer,bookDetailsFragment)
                        .addToBackStack(null)
                        .commit()

                }
            }
    }

    private fun initObserver(){
        booksViewModel.booksLiveData.observe(
            viewLifecycleOwner,
        ){

            if(it != null){

                books.addAll(it)
                booksAdaptor.notifyItemRangeInserted(0, books.size)

                for(book in it){
                    Log.e("tag", "$book")
                }
            }
        }
    }

    private fun initViewModel(){
        booksViewModel = ViewModelProvider(
            this,
            ViewMoldeFactory(
                BooksRepo(
                    (WebService.getWebService())
                )
            )
        )[BooksViewModel ::class.java]
    }

    private fun initViewsAndAdaptor(){
        binding.recyclerBooks.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        booksAdaptor = BooksAdaptor(books)
        binding.recyclerBooks.adapter = booksAdaptor

    }

    private fun getBooks() {
        val request = JsonObjectRequest(
            "https://api.itbook.store/1.0/new",
            null,
            {jsonObject ->

                val gson = Gson()

                val booksArray = gson.fromJson<Array<Book>>(
                    jsonObject.getJSONArray("books").toString(),
                    Array<Book> ::class.java
                )

                books.addAll(booksArray)

                booksAdaptor.notifyItemRangeInserted(0, books.size)

                for (book in books){
                    Log.e("book", "$book")
                }


            },
            {error ->
                Log.e("error", "error")
            }
        )

        //requestQueue.add(request)
    }


}