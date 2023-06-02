package com.soonodekar.machinetest0206.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soonodekar.machinetest0206.R
import com.soonodekar.machinetest0206.databinding.BookDetailsFragmentBinding
import com.soonodekar.machinetest0206.modules.Book
import com.squareup.picasso.Picasso

class BookDetailsFragment : Fragment(){

    private lateinit var binding: BookDetailsFragmentBinding

    var book : Book? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_details_fragment, null)
        binding = BookDetailsFragmentBinding.bind(view)
        bindData()
        setListeners(view)
        return view
    }

    private fun setListeners(view: View){
        view.setOnClickListener {

        }
    }

    private fun bindData(){
        if(book == null)return
        Picasso.get()
            .load(book!!.imageUrl)
            .into(binding.imgBookDetails)
        binding.textDetailPrice.setText(book!!.price)
        binding.textDetailTitle.setText(book!!.title)
        binding.txtIsBn13.setText(book!!.isbn13.toString())
        binding.textDetailSubtitle.setText(book!!.subtitle)
    }
}