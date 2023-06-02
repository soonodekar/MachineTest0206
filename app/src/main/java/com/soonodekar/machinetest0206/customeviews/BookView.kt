package com.soonodekar.machinetest0206.customeviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.soonodekar.machinetest0206.R
import com.soonodekar.machinetest0206.databinding.BookViewBinding
import com.soonodekar.machinetest0206.modules.Book
import com.squareup.picasso.Picasso

class BookView(context: Context, attributeSet: AttributeSet?) : ConstraintLayout(context, attributeSet){

    private val binding : BookViewBinding

    var book : Book? = null
    set(value) {
        field = value
        bindData()
    }

    constructor(context: Context) : this(context, null)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.book_view, null)
        binding = BookViewBinding.bind(view)
        this.addView(view)
    }

    private fun bindData(){
        Picasso.get()
            .load(book!!.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(binding.imgBook)

        binding.txtBookName.text = book!!.title
    }
}