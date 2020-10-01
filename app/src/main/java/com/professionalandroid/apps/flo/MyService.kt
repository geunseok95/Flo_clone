package com.professionalandroid.apps.flo

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.professionalandroid.apps.flo.MainActivity.Companion.MESSAGE_KEY

class MyService : Service() {

    lateinit var mMediaPlayer: MediaPlayer
    private var pauseposition = 0
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val message = intent?.extras?.getBoolean(MESSAGE_KEY)
        if(message!!) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.record_assignment_jang)
            mMediaPlayer.seekTo(pauseposition)
            mMediaPlayer.start()
        }
        else{
            mMediaPlayer.pause()
            pauseposition = mMediaPlayer.currentPosition
        }
        return START_STICKY
    }
}
