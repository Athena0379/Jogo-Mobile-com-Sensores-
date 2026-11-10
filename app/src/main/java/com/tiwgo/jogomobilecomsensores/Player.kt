package com.tiwgo.jogomobilecomsensores

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.FrameLayout

class Player(
    context: Context,
    spriteResId: Int,
    customWidth: Float = 150f,
    customHeight: Float = 150f
) {
    val rect: GameRect = GameRect(
        position = Vector2(0f, 0f),
        size = Vector2(customWidth, customHeight)
    )

    private val speedMultiplier: Float = 15f

    val imageView: ImageView = ImageView(context).apply {
        setImageResource(spriteResId)
        scaleType = ImageView.ScaleType.FIT_CENTER
        layoutParams = FrameLayout.LayoutParams(customWidth.toInt(), customHeight.toInt())
        visibility = android.view.View.VISIBLE
    }

    fun spawnAtStartLine(bounds: GameBounds) {
        val centerX = bounds.minBounds.x + (bounds.maxBounds.x - bounds.minBounds.x) / 2f

        rect.x = centerX
        rect.y = bounds.maxBounds.y - (rect.height / 2f)

        bounds.keepInside(rect)

        updateViewPosition()
    }

    private var velocity: Vector2 = Vector2(0f, 0f)

    fun updateWithAccelerometer(acceleration: Vector2, bounds: GameBounds) {
        val deadZone = 0.5f
        val rawX = if (kotlin.math.abs(acceleration.x) > deadZone) acceleration.x else 0f
        val rawY = if (kotlin.math.abs(acceleration.y) > deadZone) acceleration.y else 0f

        val smoothing = 0.1f
        val targetVelocityX = -rawX * speedMultiplier
        val targetVelocityY = rawY * speedMultiplier

        velocity.x += (targetVelocityX - velocity.x) * smoothing
        velocity.y += (targetVelocityY - velocity.y) * smoothing

        val maxSpeed = 25f
        velocity.x = velocity.x.coerceIn(-maxSpeed, maxSpeed)
        velocity.y = velocity.y.coerceIn(-maxSpeed, maxSpeed)

        val oldX = rect.x
        val oldY = rect.y

        rect.x += velocity.x
        rect.y += velocity.y

        bounds.keepInside(rect)

        val touchedLeft = (rect.x == bounds.minBounds.x && oldX <= bounds.minBounds.x)
        val touchedRight = (rect.x == bounds.maxBounds.x - rect.width && oldX >= bounds.maxBounds.x - rect.width)

        if (touchedLeft || touchedRight) {
            velocity.x = 0f
        }

        val touchedTop = (rect.y == bounds.minBounds.y && oldY <= bounds.minBounds.y)
        val touchedBottom = (rect.y == bounds.maxBounds.y - rect.height && oldY >= bounds.maxBounds.y - rect.height)

        if (touchedTop || touchedBottom) {
            velocity.y = 0f
        }

        updateViewPosition()
    }

    fun updateViewPosition() {
        imageView.x = rect.left
        imageView.y = rect.top
    }
}