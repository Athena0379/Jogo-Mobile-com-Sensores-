package com.tiwgo.jogomobilecomsensores

import kotlin.math.abs


object CollisionDetector {
    fun checkCollision(player: GameRect, obstacle: GameRect): Boolean {
        return player.left < obstacle.right &&
                player.right > obstacle.left &&
                player.top < obstacle.bottom &&
                player.bottom > obstacle.top
    }

    fun checkCollisionByDistance(player: GameRect, obstacle: GameRect): Boolean {
        val distanceX = abs(player.x - obstacle.x)
        val distanceY = abs(player.y - obstacle.y)

        val allowedX = (player.width / 2f) + (obstacle.width / 2f)
        val allowedY = (player.height / 2f) + (obstacle.height / 2f)

        return distanceX < allowedX && distanceY < allowedY
    }
}