package com.tiwgo.jogomobilecomsensores

class GameRect(
    val position: Vector2 = Vector2(),
    val size: Vector2 = Vector2()
) {
    var x: Float
        get() = position.x
        set(value) { position.x = value }

    var y: Float
        get() = position.y
        set(value) { position.y = value }

    var width: Float
        get() = size.x
        set(value) { size.x = value }

    var height: Float
        get() = size.y
        set(value) { size.y = value }

    val left: Float get() = x - (width / 2f)
    val right: Float get() = x + (width / 2f)
    val top: Float get() = y - (height / 2f)
    val bottom: Float get() = y + (height / 2f)
}