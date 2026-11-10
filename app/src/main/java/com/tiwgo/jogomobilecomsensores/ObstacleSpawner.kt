package com.tiwgo.jogomobilecomsensores

import android.content.Context
import android.util.Log
import kotlin.random.Random

enum class AsteroidType(val resId: Int) {
    ASTEROIDE_0(R.drawable.asteroide),
}
class ObstacleSpawner(
    private val context: Context,
    private val bounds: GameBounds,
    private var numberOfObstacles: Int
) {
    val obstacles = mutableListOf<Obstacle>()

    fun generateRandomObstacles() {
        obstacles.clear()

        val minSize = 90f
        val maxSize = 140f
        val safeZoneTop = 300f
        val safeZoneBottomOffset = 400f

        val minX = bounds.minBounds.x
        val maxX = bounds.maxBounds.x

        val minY = safeZoneTop
        val maxY = bounds.maxBounds.y - safeZoneBottomOffset

        val allAsteroids = AsteroidType.values()

        for (i in 0 until numberOfObstacles) {
            val spriteResId = allAsteroids[i % allAsteroids.size].resId

            val side = Random.nextFloat() * (maxSize - minSize) + minSize
            val size = Vector2(x = side, y = side)

            val adjustedMaxX = maxX - size.x
            val adjustedMaxY = maxY - size.y

            val randomX = Random.nextFloat() * (adjustedMaxX - minX) + minX
            val randomY = Random.nextFloat() * (adjustedMaxY - minY) + minY

            val position = Vector2(x = randomX, y = randomY)

            val tempRect = GameRect(position = position, size = size)
            bounds.keepInside(tempRect)

            val obstacle = Obstacle(context, tempRect.position, size, spriteResId)

            obstacles.add(obstacle)
        }
    }
}