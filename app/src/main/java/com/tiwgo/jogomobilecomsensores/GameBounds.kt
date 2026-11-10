package com.tiwgo.jogomobilecomsensores

data class GameBounds(
    val screenWidth: Float,
    val screenHeight: Float,
    val margin: Float = 50f
) {
    val minX: Float get() = margin
    val maxX: Float get() = screenWidth - margin

    val minY: Float get() = 0f
    val maxY: Float get() = screenHeight
}