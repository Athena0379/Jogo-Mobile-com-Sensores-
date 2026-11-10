package com.tiwgo.jogomobilecomsensores

import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView

class Obstacle(
    context: Context,
    val position: Vector2,
    val size: Vector2,
    val spriteResId: Int
) {
    val rect: GameRect = GameRect(
        position = position,
        size = size
    )

    val imageView: ImageView = ImageView(context).apply {
        setImageResource(spriteResId)
        layoutParams = FrameLayout.LayoutParams(size.x.toInt(), size.y.toInt()).apply {
            leftMargin = rect.left.toInt()
            topMargin = rect.top.toInt()
        }
        visibility = android.view.View.VISIBLE
    }
}