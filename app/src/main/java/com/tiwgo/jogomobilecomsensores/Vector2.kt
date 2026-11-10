package com.tiwgo.jogomobilecomsensores

data class Vector2(
    var x: Float = 0f,
    var y: Float = 0f
) {
    val width: Float get() = x
    val height: Float get() = y
}