package com.tiwgo.jogomobilecomsensores

import kotlin.math.abs

object CollisionDetector {

    fun checkCollision(
        playerX: Float, playerY: Float, playerW: Float, playerH: Float,
        obstacleX: Float, obstacleY: Float, obstacleW: Float, obstacleH: Float
    ): Boolean {
        return playerX < obstacleX + obstacleW &&
                playerX + playerW > obstacleX &&
                playerY < obstacleY + obstacleH &&
                playerY + playerH > obstacleY
    }

    fun checkCollisionByDistance(
        pX: Float, pY: Float, pW: Float, pH: Float,
        oX: Float, oY: Float, oW: Float, oH: Float
    ): Boolean {
        val pCenterX = pX + pW / 2f
        val pCenterY = pY + pH / 2f

        val oCenterX = oX + oW / 2f
        val oCenterY = oY + oH / 2f

        val distanceX = abs(pCenterX - oCenterX)
        val distanceY = abs(pCenterY - oCenterY)

        val allowedX = (pW / 2f) + (oW / 2f)
        val allowedY = (pH / 2f) + (oH / 2f)

        return distanceX < allowedX && distanceY < allowedY
    }
}