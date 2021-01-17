package com.hoang.moviedblearning.utils

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.hoang.moviedblearning.BuildConfig
import java.io.File

@BindingAdapter(
    value = ["loadImage", "localBackground", "placeholder", "centerCrop", "fitCenter", "circleCrop",
        "cacheSource", "animation"],
    requireAll = false
)
fun ImageView.loadImage(
    imageUrl: String? = "",
    localImage: Drawable? = null,
    placeHolder: Drawable? = null,
    centerCrop: Boolean = false,
    fitCenter: Boolean = false,
    circleCrop: Boolean = false,
    isCacheSource: Boolean = false,
    animation: Boolean = false
) {
    if (TextUtils.isEmpty(imageUrl)) {
        setImageDrawable(placeHolder)
        return
    }

    val requestBuilder: RequestBuilder<Drawable>
    val localImageId =
        context.resources.getIdentifier(imageUrl, "drawable", BuildConfig.APPLICATION_ID)
    requestBuilder = if (localImageId != 0) {
        // Load image from local
        localImage?.let { background = localImage }
        Glide.with(context).load(localImageId)
    } else {
        // image is not in local, but may be in server.
        Glide.with(context).load(imageUrl)
    }
    val diskCacheStrategy = if (isCacheSource) {
        DiskCacheStrategy.DATA
    } else {
        DiskCacheStrategy.RESOURCE
    }
    val requestOptions =
        RequestOptions().diskCacheStrategy(diskCacheStrategy).placeholder(placeHolder)
    if (!animation) {
        requestOptions.dontAnimate()
    }
    if (centerCrop) {
        requestOptions.centerCrop()
    }
    if (fitCenter) {
        requestOptions.fitCenter()
    }
    if (circleCrop) {
        requestOptions.circleCrop()
    }
    val file = File(imageUrl ?: return)
    if (file.exists()) {
        requestOptions.signature(ObjectKey(file.lastModified().toString()))
    }
    requestBuilder.apply(requestOptions).into(this)
}