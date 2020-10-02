package com.professionalandroid.apps.flo

import android.R.raw
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.professionalandroid.apps.flo.MainActivity.Companion.MESSAGE_KEY
import kotlin.Unit as Unit1


class MyService : Service() {

    var mMediaPlayer: MediaPlayer? = null
    private var pauseposition = 0
    var now_song = 0


//    val music_list = fun(): List<Int>? {
//        val temp= mutableListOf<Int>()
//        for (field in raw::class.java.fields) {
//            try {
//                temp.add(field.getInt(field))
//            } catch (e: Exception) {
//
//            }
//        }
//        return temp
//    }

    // Bind service
    private val binder = LocalBinder()
    override fun onBind(intent: Intent): IBinder {
        return binder
    }
    inner class LocalBinder: Binder(){
        fun getService(): MyService = this@MyService
    }

    val musiclist = arrayOf(R.raw.gilgubonggu_propose, R.raw.bts_dynamite, R.raw.cash_cash_how_to_love_feat_sofia_reyes)
    val titlelist = arrayOf("프로포즈", "Dynamite", "How To Love (feat. Sofia Reyes)")
    val artistlist = arrayOf("길구봉구", "방탄소년단", "Cash Cash")

    override fun onCreate() {
        super.onCreate()
        mMediaPlayer = MediaPlayer.create(this, musiclist[now_song])

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

    if(mMediaPlayer == null) {
        intent?.let {
                mMediaPlayer?.let { it ->
                    it.setOnCompletionListener { _ -> stopSelf() }
                    it.seekTo(pauseposition)
                    it.start()
                    Log.d("test", "$pauseposition")
                    Log.d("test", "service start")
                }
            }
        }
        return START_NOT_STICKY
    }


//        val message = intent?.extras?.getBoolean(MESSAGE_KEY)
//        if(message!!) {
//
//            mMediaPlayer = MediaPlayer.create(this, musiclist[0])
//            mMediaPlayer.seekTo(pauseposition)
//            mMediaPlayer.start()
//        }
//        else{
//            mMediaPlayer.pause()
//            pauseposition = mMediaPlayer.currentPosition
//        }
//        return START_STICKY
//    }


    fun musicRestart(){
        playsong(now_song, pauseposition)
        Log.d("test", "music start")
        Log.d("test", "$pauseposition")

    }

    fun musicPause(){
        mMediaPlayer?.pause()
        pauseposition = mMediaPlayer!!.currentPosition
        Log.d("test", "music pause")
        Log.d("test", "$pauseposition")

    }

    fun musicNext(){
        mMediaPlayer?.stop()
        now_song++
        if(now_song >= musiclist.size)   now_song = 0
        pauseposition = 0
        playsong(now_song,pauseposition)
    }

    fun musicPrevious(){
        mMediaPlayer?.stop()
        now_song--
        if(now_song < 0)   now_song = musiclist.size - 1
        pauseposition = 0
        playsong(now_song, pauseposition)
    }

    fun playsong(now_song: Int, pauseposition: Int){
        if(pauseposition == 0) {
            mMediaPlayer = MediaPlayer.create(this, musiclist[now_song])
        }
        mMediaPlayer?.seekTo(pauseposition)
        mMediaPlayer?.start()

        mMediaPlayer?.setOnCompletionListener { musicNext() }
    }


    override fun onDestroy() {  //서비스 종료
        super.onDestroy()
        mMediaPlayer?.release()
        mMediaPlayer = null

        Log.d("test", "service destroy")
    }


}
