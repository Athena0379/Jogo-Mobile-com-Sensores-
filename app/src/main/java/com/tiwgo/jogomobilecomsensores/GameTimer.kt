package com.tiwgo.jogomobilecomsensores

import android.os.SystemClock

class GameTimer {
    var startTime: Long = 0L
    var elapsedTime: Long = 0L
    private var isRunning: Boolean = false

    fun start(){
        startTime = SystemClock.elapsedRealtime()
        elapsedTime = 0L
        isRunning = true
    }

    fun getElapsedTimeMillis(): Long {
        if (isRunning){
            elapsedTime = SystemClock.elapsedRealtime() - startTime
        }
        return elapsedTime
    }

    fun stop(): Long {
        if (isRunning){
            elapsedTime = SystemClock.elapsedRealtime() - startTime
            isRunning = false
        }
        return elapsedTime
    }

    fun getFormattedTime(): String{
        var totalMillis = getElapsedTimeMillis()
        var minutes = (totalMillis / 1000) / 60
        var seconds = (totalMillis / 1000) % 60

        return String.format("%02d:%02d", minutes, seconds)
    }

    fun calculateScore(baseScore: Int = 10000, timePenalty: Int = 100): Int{
        var totalSeconds = getElapsedTimeMillis() / 1000.0
        var score = baseScore - (totalSeconds * timePenalty).toInt()

        return maxOf(score, 0)
    }

    fun isRunning(): Boolean = isRunning
}