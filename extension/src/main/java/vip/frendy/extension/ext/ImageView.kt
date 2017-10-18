package vip.frendy.extension.ext

import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


/**
 * Created by frendy on 2017/6/19.
 */

fun ImageView.loadImageCircle(url: String) {
    Glide.with(context).load(url).asBitmap().centerCrop().into(object : BitmapImageViewTarget(this) {
        override fun setResource(resource: Bitmap) {
            val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource)
            circularBitmapDrawable.isCircular = true
            setImageDrawable(circularBitmapDrawable)
        }
    })
}

fun ImageView.loadImageRoundedCorners(url: String, radius: Int, margin: Int, cornerType: RoundedCornersTransformation.CornerType) {
    Glide.with(context).load(url)
            .bitmapTransform(RoundedCornersTransformation(context, radius, margin, cornerType))
            .into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).centerCrop().into(this)
}

fun ImageView.loadImage(imageId: Int) {
    Glide.with(context).load(imageId).fitCenter().into(this)
}

fun ImageView.loadImageColorHolder(url: String, holderColor: Int) {
    Glide.with(context).load(url)
            .placeholder(holderColor)
            .centerCrop().into(this)
}

fun ImageView.loadImageFliter(url: String, color: Int) {
    Glide.with(context).load(url).centerCrop()
            .bitmapTransform(ColorFilterTransformation(context, color))
            .into(this)
}