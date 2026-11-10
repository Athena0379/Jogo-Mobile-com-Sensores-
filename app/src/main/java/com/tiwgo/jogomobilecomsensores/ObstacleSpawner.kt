package com.tiwgo.jogomobilecomsensores

import kotlin.random.Random
data class Obstacle(
    var x: Float,
    var y: Float,
    val width: Float,
    val height: Float,
    val spriteResId: Int
)

class ObstacleSpawner(
    private val bounds: GameBounds,
    private var numberOfObstacles: Int = 5,
    private var obstacleSpriteResId: Int = 0
) {
    val obstacles = mutableListOf<Obstacle>()

    fun generateRandomObstacles() {
        obstacles.clear()

        val minWidth = 90f
        val maxWidth = 140f
        val safeZoneTop = bounds.minY + 300f
        val safeZoneBottom = bounds.maxY - 400f

        for (i in 0 until numberOfObstacles) {
            val width = Random.nextFloat() * (maxWidth - minWidth) + minWidth
            val height = width

            val x = Random.nextFloat() * (bounds.maxX - bounds.minX - width) + bounds.minX
            val y = Random.nextFloat() * (safeZoneBottom - safeZoneTop) + safeZoneTop

            obstacles.add(Obstacle(x, y, width, height, obstacleSpriteResId))
        }
    }
}