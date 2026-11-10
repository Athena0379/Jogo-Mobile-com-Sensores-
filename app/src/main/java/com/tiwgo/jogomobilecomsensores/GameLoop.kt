package com.tiwgo.jogomobilecomsensores

import android.view.Choreographer

class GameLoop(val onUpdate:(deltaTime: Float) -> Unit): Choreographer.FrameCallback {

    var isRunning: Boolean = false
    var lastFrameTimeNanos: Long = 0L

    fun start(){
        if(isRunning) return
        isRunning = true
        lastFrameTimeNanos = System.nanoTime()
        Choreographer.getInstance().postFrameCallback(this)
    }
    fun stop(){
        isRunning = false
        Choreographer.getInstance().removeFrameCallback(this)
    }

    override fun doFrame(frameTimeNanos: Long){
        if (!isRunning) return

        var deltaTime = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000.0f
        lastFrameTimeNanos = frameTimeNanos
        onUpdate(deltaTime)

        Choreographer.getInstance().postFrameCallback(this)
    }
}