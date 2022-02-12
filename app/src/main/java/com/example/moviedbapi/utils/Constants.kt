package com.example.moviedbapi.utils

import android.media.Image

object Constants {
    const val api_key = "ca2089e88d8bae92bbd1415301053501"
    const val BASE_URL = "https://api.themoviedb.org"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val QUERY_PAGE_SIZE = 20
    //popular
    //https://api.themoviedb.org/3/movie/popular?api_key=ca2089e88d8bae92bbd1415301053501&page=1
    //image
    //https://image.tmdb.org/t/p/w500
}


//        val myCallback = object : MyCallback {
//            override fun update(state: DataState) {
//                runOnUiThread {
//                    textView.text = state.show()
//                }
//            }
//        }
//
//        val mySdk = MySdk()
//        button.setOnClickListener {
//            mySdk.getData(myCallback)
//        }