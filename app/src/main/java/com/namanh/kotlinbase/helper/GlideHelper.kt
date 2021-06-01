package com.namanh.kotlinbase.helper

import android.content.Context
import android.widget.ImageView
import com.namanh.kotlinbase.R
import com.namanh.kotlinbase.utils.GlideApp

object GlideHelper {
    fun loadImage(context: Context?, view: ImageView?, urlImage: String?) {
        if (context == null || view == null || urlImage == null || urlImage.trim { it <= ' ' }
                .isEmpty()) return
        var formatUrlImage = urlImage
        if (urlImage.startsWith("http://")) {
            formatUrlImage = urlImage.replace("http://", "https://")
        }
        GlideApp.with(context)
            .load(formatUrlImage)
            .error(R.drawable.ic_launcher_foreground)
            .into(view)
    }
}