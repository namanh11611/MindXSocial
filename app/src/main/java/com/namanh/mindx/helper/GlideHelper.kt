package com.namanh.mindx.helper

import android.content.Context
import android.widget.ImageView
import com.namanh.mindx.R
import com.namanh.mindx.utils.GlideApp

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