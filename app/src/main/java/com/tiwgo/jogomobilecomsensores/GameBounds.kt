package com.tiwgo.jogomobilecomsensores

class GameBounds(
    val screenSize: Vector2,
    val margin: Float = 0f
) {
    val minBounds: Vector2 = Vector2(margin, margin)
    val maxBounds: Vector2 = Vector2(screenSize.x - margin, screenSize.y - margin)

    fun keepInside(rect: GameRect) {
        if (rect.x < minBounds.x) {
            rect.x = minBounds.x
        }

        if (rect.x > maxBounds.x) {
            rect.x = maxBounds.x
        }

        if (rect.y < minBounds.y) {
            rect.y = minBounds.y
        }

        if (rect.y > maxBounds.y) {
            rect.y = maxBounds.y
        }
    }
}