package com.soonodekar.machinetest0206.adaptors

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soonodekar.machinetest0206.customeviews.BookView
import com.soonodekar.machinetest0206.modules.Book

class BooksAdaptor(private val books : ArrayList<Book>) : RecyclerView.Adapter<BooksAdaptor.BooksViewHolder>() {

    interface BookClickLisetner{
        fun onBookClick(book: Book)
    }

    var bookClickListeners : BookClickLisetner? = null

    inner class BooksViewHolder(val bookView: BookView) : RecyclerView.ViewHolder(bookView){

        init {
            bookView.setOnClickListener {
                bookClickListeners!!.onBookClick(books[adapterPosition])
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            BookView(parent.context)
        )
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bookView.book = books[position]
    }
}