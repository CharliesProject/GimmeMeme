package com.example.restapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.restapp.network.SinglePhoto
import com.example.restapp.overview.MyApiStatus
import com.example.restapp.overview.OverviewFragmentAdapter


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<SinglePhoto>?
) {
    val adapter = recyclerView.adapter as OverviewFragmentAdapter
    adapter.submitList(data)
}

@BindingAdapter("myApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MyApiStatus?) {
    when (status) {
        MyApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MyApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MyApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}