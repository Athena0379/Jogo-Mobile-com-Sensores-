package com.tiwgo.jogomobilecomsensores

import android.view.Choreographer

class GameLoop(val onUpdate:() -> Unit): Choreographer.FrameCallback {

    var isRunning: Boolean = false

    fun start(){
        if(isRunning) return
        isRunning = true
        Choreographer.getInstance().postFrameCallback(this)
    }
    fun stop(){
        isRunning = false
        Choreographer.getInstance().removeFrameCallback(this)
    }

    override fun doFrame(frameTimeNanos: Long){
        if (!isRunning) return

        onUpdate()

        Choreographer.getInstance().postFrameCallback(this)
    }
}