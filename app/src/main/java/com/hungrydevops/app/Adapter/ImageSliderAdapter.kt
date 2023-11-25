package com.hungrydevops.app.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager.widget.PagerAdapter
import com.hungrydevops.app.R

class ImageSliderAdapter(private val context: Context, private val images: IntArray): PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

//    @SuppressLint("MissingInflatedId")
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.image_item, container, false)
//
//        val imageView: ImageView = view.findViewById(R.id.imageView)
//        imageView.setImageResource(images[position])
//
//        container.addView(view)
//        return view
//    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}