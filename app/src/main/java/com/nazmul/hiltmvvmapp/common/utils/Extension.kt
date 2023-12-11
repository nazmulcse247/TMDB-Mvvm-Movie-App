package com.nazmul.hiltmvvmapp.common.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nazmul.hiltmvvmapp.R

fun ImageView.loadImage(url : String, imageTypeEnum: ImageTypeEnum) {

    val placeHolder = when(imageTypeEnum) {
        ImageTypeEnum.CREDIT -> R.drawable.grid_shimmer_card_bg
        ImageTypeEnum.REGULAR -> R.drawable.grid_shimmer_card_bg
    }

    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(this.context.circularProgressDrawable())
        .error(placeHolder)
        .into(this)

}

fun Context.circularProgressDrawable(): Drawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 7f
        centerRadius = 60f
        setColorSchemeColors(
            androidx.core.content.ContextCompat.getColor(
                this@circularProgressDrawable,
                R.color.text_color
            )
        )
        start()
    }
}