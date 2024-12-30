package com.example.circuithouseassignment.ui.components

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.circuithouseassignment.R

@Composable
fun GlideImage(imageUrl: String?, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        modifier = modifier,
        update = { imageView ->
            Glide.with(context)
                .load(imageUrl?: context.getString(R.string.default_image) )
                .into(imageView)
        }
    )
}
