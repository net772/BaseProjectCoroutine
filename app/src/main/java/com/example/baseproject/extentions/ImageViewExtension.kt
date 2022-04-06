package com.example.baseproject.extentions

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.baseproject.R
import org.jetbrains.anko.dimen

fun ImageView.setFitCenter(url: String) {
    Glide.with(context)
            .load(url)
            .fitCenter()
            .into(this)
}

fun ImageView.setRoundFitCenter(url: String, @DimenRes dp: Int = R.dimen._10dp) {
    Glide.with(context)
            .load(url)
            .transform(FitCenter(), RoundedCorners(context.dimen(dp)))
            .into(this)
}

fun ImageView.setRoundCenterCrop(url: String, @DimenRes dp: Int = R.dimen._10dp) {
    Glide.with(context)
            .load(url)
            .transform(CenterCrop(), RoundedCorners(context.dimen(dp)))
            .into(this)
}

fun ImageView.setRoundCenterCropUri(uri: Uri, @DimenRes dp: Int = R.dimen._10dp) {
    Glide.with(context)
            .load(uri)
            .transform(CenterCrop(), FitCenter(), RoundedCorners(context.dimen(dp)))
            .skipMemoryCache(false)
            .into(this)
}

fun ImageView.setCircleCenterCrop(url: String) {
    Glide.with(context)
            .load(url)
            .circleCrop()
            .into(this)
}

fun ImageView.setCenterCrop(url: String) {
    Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
}

fun AppCompatImageView.setAppCompatCenterCrop(url: String) {
    Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
}

fun ImageView.setImage(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}

fun ImageView.setImage(@DrawableRes resourceId: Int) {
    Glide.with(context)
            .load(resourceId)
            .into(this)
}



